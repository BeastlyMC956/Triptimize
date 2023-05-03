package com.beastlymc.triptimize.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * A service for managing user accounts in the system.
 */
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private static final String EMAIL_NOT_FOUND_MSG = "user with email '%s' not found";
    private UserRepository userRepository;

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
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(String.format(email,
                EMAIL_NOT_FOUND_MSG)));
    }
}
