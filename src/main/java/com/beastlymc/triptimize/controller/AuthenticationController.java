package com.beastlymc.triptimize.controller;

import com.beastlymc.triptimize.constants.AuthenticationConstants;
import com.beastlymc.triptimize.dto.request.AuthenticationRequest;
import com.beastlymc.triptimize.dto.request.RegisterRequest;
import com.beastlymc.triptimize.model.account.Account;
import com.beastlymc.triptimize.service.AccountService;
import com.beastlymc.triptimize.service.AuthenticationService;
import com.beastlymc.triptimize.util.Util;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller class for authentication-related endpoints in the API.
 */
@RequiredArgsConstructor
@RequestMapping(Util.DEFAULT_API_PATH + "auth")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AccountService accountService;

    /**
     * Registers a new user with the system.
     *
     * @param request a RegisterRequest object representing the details of the user to register
     * @return a ResponseEntity containing an AuthenticationResponse object with a JWT token for the
     * registered user
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        Optional<Account> accountRequest = accountService.loadUser(request.getEmail(),
            request.getUsername());
        if (accountRequest.isPresent()) {
            String conflict = accountRequest.get().getEmail().equals(request.getEmail())
                ? AuthenticationConstants.MESSAGE_EMAIL_ALREADY_IN_USE
                : AuthenticationConstants.MESSAGE_USERNAME_ALREADY_IN_USE;
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(conflict);
        }

        accountService.sendVerificationEmail(request.getEmail());

        return authenticationService.register(request);
    }

    /**
     * Authenticates a user with the system.
     * <p> If the authentication is successful, a JWT token is generated for the user and returned
     * in the response.
     *
     * @param request  an AuthenticationRequest object representing the user's credentials
     * @param response the HttpServletResponse object for the request
     * @return a ResponseEntity containing an AuthenticationResponse object with a JWT token for the
     * authenticated user
     */
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
        @RequestBody AuthenticationRequest request, HttpServletResponse response) {
        return authenticationService.authenticate(request, response);
    }
}
