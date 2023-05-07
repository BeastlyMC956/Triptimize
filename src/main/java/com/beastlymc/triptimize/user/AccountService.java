package com.beastlymc.triptimize.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AccountService implements UserDetailsService {
    
    private static final String EMAIL_NOT_FOUND_MSG = "user with email '%s' not found";
    private AccountRepository accountRepository;

    /**
    * Loads a user by their email address.
    *
    * <p>If a user with the specified email address is not found, a
    * {@link UsernameNotFoundException} is thrown.</p>
    *
    * @param email the email address of the user to load
    * @return the UserDetails representing the loaded user account
    * @throws UsernameNotFoundException if no user with the specified email address is found
    */
    @Override
    public Account loadUserByUsername(String email) {
        return accountRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(String.format(email,
                EMAIL_NOT_FOUND_MSG)));
    }
}
