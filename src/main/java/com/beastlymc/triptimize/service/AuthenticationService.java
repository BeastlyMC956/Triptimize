package com.beastlymc.triptimize.service;

import static com.beastlymc.triptimize.constants.AuthenticationConstants.MESSAGE_ACCOUNT_LOCKED;
import static com.beastlymc.triptimize.constants.AuthenticationConstants.MESSAGE_ACCOUNT_NOT_VERIFIED;
import static com.beastlymc.triptimize.constants.AuthenticationConstants.MESSAGE_EMAIL_NOT_FOUND;
import static com.beastlymc.triptimize.constants.AuthenticationConstants.MESSAGE_PASSWORD_INCORRECT;

import com.beastlymc.triptimize.dto.request.AuthenticationRequest;
import com.beastlymc.triptimize.dto.request.RegisterRequest;
import com.beastlymc.triptimize.model.account.Account;
import com.beastlymc.triptimize.model.account.Profile;
import com.beastlymc.triptimize.model.account.Role;
import com.beastlymc.triptimize.repository.AccountRepository;
import com.beastlymc.triptimize.util.Util;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * A service class for authentication-related logic in the application.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user in the system and generates a JWT token for them.
     *
     * @param request a RegisterRequest object representing the details of the user to register
     * @return an AuthenticationResponse object containing a JWT token for the registered user
     */
    public ResponseEntity<?> register(@NotNull RegisterRequest request) {
        var newProfile = Profile.builder().username(request.getUsername()).build();
        var verification = accountService.sendVerificationEmail(request.getEmail());

        var newAccount = Account.builder()
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.ROLE_USER)
            .accountCreationDate(Util.getCurrentSQLDate())
            .profile(newProfile)
            .verification(verification)
            .build();

        accountRepository.save(newAccount);

        return ResponseEntity.ok().build();
    }

    /**
     * Authenticates a user with the given credentials and generates a JWT token for them.
     *
     * @param request  an AuthenticationRequest object representing the user's credentials
     * @param response the HttpServletResponse object to add the JWT token to
     * @return a ResponseEntity object representing the result of the authentication attempt
     */
    public ResponseEntity<?> authenticate(@NotNull AuthenticationRequest request,
        @NotNull HttpServletResponse response) {
        var account = accountRepository.findByEmail(request.getEmail());

        if (account.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(MESSAGE_EMAIL_NOT_FOUND);
        }

        var authentication = new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        );

        if (!request.getPassword().equals(authentication.getCredentials().toString())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(MESSAGE_PASSWORD_INCORRECT);
        }

        var emailAccount = account.get();

        if (emailAccount.isLocked()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(MESSAGE_ACCOUNT_LOCKED);
        } else if (!emailAccount.isVerified()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(MESSAGE_ACCOUNT_NOT_VERIFIED);
        }

        authenticationManager.authenticate(authentication);

        emailAccount.setLastLoginDate(Util.getCurrentSQLDate());
        accountRepository.save(emailAccount);

        var jwtToken = jwtService.generateToken(emailAccount);
        response.addCookie(jwtService.generateCookie(jwtToken));

        return ResponseEntity.ok().build();
    }
}
