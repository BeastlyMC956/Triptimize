package com.beastlymc.triptimize.service;

import com.beastlymc.triptimize.dto.request.AuthenticationRequest;
import com.beastlymc.triptimize.dto.request.RegisterRequest;
import com.beastlymc.triptimize.dto.response.AuthenticationResponse;
import com.beastlymc.triptimize.model.account.Account;
import com.beastlymc.triptimize.model.account.Role;
import com.beastlymc.triptimize.model.account.profile.Location;
import com.beastlymc.triptimize.model.account.profile.Profile;
import com.beastlymc.triptimize.repository.AccountRepository;
import com.beastlymc.triptimize.util.Util;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user in the system and generates a JWT token for them.
     *
     * @param request a RegisterRequest object representing the details of the user to register
     * @return an AuthenticationResponse object containing a JWT token for the registered user
     */
    public AuthenticationResponse register(@NotNull RegisterRequest request) {
        var newLocation = Location.builder()
            .country(request.getCountry())
            .preferredCurrency(request.getPreferredCurrency())
            .build();

        var newProfile = Profile.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .dateOfBirth(request.getDateOfBirth())
            .travelPreferences(request.getTravelPreferences())
            .profilePicture(request.getProfilePicture())
            .location(newLocation)
            .build();

        var newAccount = Account.builder()
            .email(request.getEmail())
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .accountCreationDate(Util.getCurrentSQLDate())
            .lastLoginDate(Util.getCurrentSQLDate())
            .role(Role.ROLE_USER)
            .profile(newProfile)
            .authoredItineraries(Collections.emptySet())
            .build();

        accountRepository.save(newAccount);

        var jwtToken = jwtService.generateToken(newAccount);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    /**
     * Authenticates a user with the given credentials and generates a JWT token for them.
     *
     * @param request  an AuthenticationRequest object representing the user's credentials
     * @param response the HttpServletResponse object to add the JWT token to
     * @return a ResponseEntity object representing the result of the authentication attempt
     */
    public ResponseEntity<?> authenticate(@NotNull AuthenticationRequest request,
        HttpServletResponse response) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        var account = accountRepository.findByEmail(request.getEmail()).orElseThrow();

        account.setLastLoginDate(Util.getCurrentSQLDate());

        var jwtToken = jwtService.generateToken(account);
        final int ONE_WEEK = 60 * 60 * 24 * 7;

        Cookie jwtCookie = new Cookie("token", jwtToken);
        jwtCookie.setMaxAge(ONE_WEEK);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");

        response.addCookie(jwtCookie);

        return ResponseEntity.ok().build();
    }
}
