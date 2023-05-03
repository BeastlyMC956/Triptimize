package com.beastlymc.triptimize.auth;

import com.beastlymc.triptimize.security.JwtService;
import com.beastlymc.triptimize.user.Role;
import com.beastlymc.triptimize.user.User;
import com.beastlymc.triptimize.user.UserRepository;
import java.sql.Date;
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

    private final UserRepository repository;
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
        var user = User.builder()
            .username(request.getUsername())
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .country(request.getCountry())
            .accountCreationDate(new Date(System.currentTimeMillis()))
            .dateOfBirth(request.getDateOfBirth())
            .travelPreferences(request.getTravelPreferences())
            .profilePicture(request.getProfilePicture())
            .preferredCurrency(request.getPreferredCurrency())
            .lastLoginDate(new Date(System.currentTimeMillis()))
            .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
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
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
