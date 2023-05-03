package com.beastlymc.triptimize.security.config;

import com.beastlymc.triptimize.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * A configuration class for various settings in the application.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    /**
     * Returns a UserDetailsService implementation that loads user details from the UserRepository.
     *
     * @return a UserDetailsService that loads user details from the UserRepository
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not Found"));
    }

    /**
     * Returns an AuthenticationProvider implementation that uses the userDetailsService to
     * authenticate users.
     *
     * @return an AuthenticationProvider that uses the userDetailsService to authenticate users
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Returns an AuthenticationManager bean for the application.
     *
     * @param config the AuthenticationConfiguration object for the application
     * @return an AuthenticationManager object for the application
     * @throws Exception if an error occurs while building the AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(@NotNull AuthenticationConfiguration config)
        throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Returns a PasswordEncoder implementation for the application.
     *
     * @return a PasswordEncoder implementation for the application
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
