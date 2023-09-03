package com.beastlymc.triptimize.controller.global;

import com.beastlymc.triptimize.constants.AuthenticationConstants;
import com.beastlymc.triptimize.dto.request.VerificationRequest;
import com.beastlymc.triptimize.dto.response.PublicAccountResponse;
import com.beastlymc.triptimize.service.AccountService;
import com.beastlymc.triptimize.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for public {@link com.beastlymc.triptimize.model.account.Account} objects.
 */
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
@RequestMapping(Util.PUBLIC_API_PATH + "accounts")
@RestController
public class PublicAccountController {

    private final AccountService accountService;

    /**
     * Gets public information about a user by their id.
     *
     * @param accountId the id of the user to search for
     * @return a PublicUserResponse object containing the public information about the user
     */
    @GetMapping("{accountId}")
    public ResponseEntity<PublicAccountResponse> getAccountDetailById(
        @PathVariable("accountId") Integer accountId) {
        var acc = accountService.findById(accountId);
        return acc.map(account -> ResponseEntity.ok(
                PublicAccountResponse.builder().id(accountId).username(account.getUsername()).build()))
            .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("verify")
    public ResponseEntity<?> verifyAccount(@RequestBody VerificationRequest request) {
        if (accountService.isVerified(request.getEmail())) {
            return ResponseEntity.badRequest()
                .body(AuthenticationConstants.MESSAGE_ACCOUNT_VERIFICATION_NOT_REQUIRED);
        }

        if (accountService.verifyAccount(request.getEmail(), request.getVerificationCode())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest()
                .body(AuthenticationConstants.MESSAGE_ACCOUNT_INVALID_VERIFICATION_CODE);
        }
    }

}
