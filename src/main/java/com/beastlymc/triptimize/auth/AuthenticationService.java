package com.beastlymc.triptimize.auth;

import com.beastlymc.triptimize.security.JwtService;
import com.beastlymc.triptimize.user.Account;
import com.beastlymc.triptimize.user.AccountRepository;
import com.beastlymc.triptimize.user.Role;
import com.beastlymc.triptimize.user.profile.Location;
import com.beastlymc.triptimize.user.profile.Profile;

import java.sql.Date;
import java.time.LocalDate;

import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
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
            .accountCreationDate(Date.valueOf(Long.toString(System.currentTimeMillis())))
            .lastLoginDate(Date.valueOf(Long.toString(System.currentTimeMillis())))
            .role(Role.USER)
            .profile(newProfile)
            .authoredItineraries(null)
            .collaboratedItineraries(null)
            .build();

        accountRepository.save(newAccount);

        var jwtToken = jwtService.generateToken(newAccount);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    /**
     * Authenticates a user in the system and generates a JWT token for them.
     *
     * @param request an AuthenticationRequest object representing the user's credentials
     * @return an AuthenticationResponse object containing a JWT token for the authenticated user
     */
    public AuthenticationResponse authenticate(@NotNull AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );
        var account = accountRepository.findByEmail(request.getEmail()).orElseThrow();

        account.setLastLoginDate(Date.valueOf(Long.toString(System.currentTimeMillis())));

        var jwtToken = jwtService.generateToken(account);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
