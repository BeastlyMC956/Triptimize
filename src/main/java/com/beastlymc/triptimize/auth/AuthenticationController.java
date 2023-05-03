package com.beastlymc.triptimize.auth;

import com.beastlymc.triptimize.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller class for authentication-related endpoints in the API.
 */
@RequiredArgsConstructor
@RequestMapping(Constants.DEFAULT_API_PATH + "auth")
@RestController
public class AuthenticationController {

    private final AuthenticationService service;

    /**
     * Registers a new user with the system.
     *
     * @param request a RegisterRequest object representing the details of the user to register
     * @return a ResponseEntity containing an AuthenticationResponse object with a JWT token for the
     * registered user
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    /**
     * Authenticates a user with the system.
     *
     * @param request an AuthenticationRequest object representing the user's credentials
     * @return a ResponseEntity containing an AuthenticationResponse object with a JWT token for the
     * authenticated user
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
        @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

}
