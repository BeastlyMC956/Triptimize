package com.beastlymc.triptimize.controller;

import com.beastlymc.triptimize.dto.request.RegisterRequest;
import com.beastlymc.triptimize.model.account.Account;
import com.beastlymc.triptimize.service.AccountService;
import com.beastlymc.triptimize.util.Util;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(Util.DEFAULT_API_PATH + "accounts")
@RestController
public class AccountController {

    private final AccountService accountService;

    @PreAuthorize("hasRole('ADMIN') or hasPermission(#accountId, 'Account', 'update')")
    @PutMapping("{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable("accountId") Integer accountId,
        @RequestBody RegisterRequest request) {

        return null;
    }

    @PreAuthorize("hasRole('ADMIN') or hasPermission(#accountId, 'Account', 'delete')")
    @DeleteMapping("{accountId}")
    public ResponseEntity<Account> deleteAccount(@PathVariable("accountId") Integer accountId) {
        var acc = accountService.findById(accountId);
        if (acc.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        accountService.deleteById(accountId);
        return ResponseEntity.ok(acc.get());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.findAll());
    }
}
