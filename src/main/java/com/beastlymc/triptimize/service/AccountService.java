package com.beastlymc.triptimize.service;

import com.beastlymc.triptimize.constants.AuthenticationConstants;
import com.beastlymc.triptimize.dto.request.RegisterRequest;
import com.beastlymc.triptimize.model.account.Account;
import com.beastlymc.triptimize.model.account.Profile;
import com.beastlymc.triptimize.model.account.Verification;
import com.beastlymc.triptimize.repository.AccountRepository;
import com.beastlymc.triptimize.repository.ProfileRepository;
import com.beastlymc.triptimize.repository.VerificationRepository;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccountService implements UserDetailsService {

    private AccountRepository accountRepository;
    private ProfileRepository profileRepository;
    private VerificationRepository verificationRepository;


    /**
     * Loads a user by their email address or username.
     *
     * @param email    the email address of the user to load
     * @param username the username of the user to load
     * @return an {@link Optional} containing the user if found, or an empty Optional otherwise
     */
    public Optional<Account> loadUser(String email, String username) {
        Optional<Profile> byUsername = profileRepository.findByUsername(username);
        return accountRepository.findByEmail(email)
            .or(() -> byUsername.map(Profile::getAccount));
    }

    /**
     * Sends a verification email to the specified email address.
     *
     * @param email the email address to send the verification email to
     */
    public Verification sendVerificationEmail(@NotNull String email) {
        Random random = new Random(System.currentTimeMillis() + email.hashCode());
        int code = random.nextInt(100000, 999999);

        long roundedTime =
            System.currentTimeMillis() + (60 * 1000) - (System.currentTimeMillis() % (60 * 1000));
        Timestamp roundedHourFromNow = new Timestamp(roundedTime);

        return Verification.builder()
            .verificationCode(code)
            .expirationDate(roundedHourFromNow).build();

        // Send the verification code to the user's email address
    }

    public boolean isVerified(@NotNull String email) {
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isEmpty()) {
            return false;
        }
        Account acc = account.get();

        return acc.isVerified();
    }

    public boolean verifyAccount(@NotNull String email, @NotNull String verificationCode) {
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isEmpty()) {
            return false;
        }
        Optional<Verification> optionalVerification = verificationRepository.findByAccount(
            account.get());
        if (optionalVerification.isEmpty()) {
            return false;
        }
        Verification verification = optionalVerification.get();
        if (verification.getExpirationDate().before(new Date(System.currentTimeMillis()))) {
            return false;
        }
        if (verification.getVerificationCode() != Integer.parseInt(verificationCode)) {
            return false;
        }
        Account verfiedAccount = account.get();

        verfiedAccount.setVerified(true);
        verfiedAccount.setVerification(null);
        verificationRepository.delete(verification);
        accountRepository.save(verfiedAccount);

        return true;
    }

    /**
     * Loads a user by their username.
     *
     * <p>If a user with the specified email address is not found, a
     * {@link UsernameNotFoundException} is thrown.</p>
     *
     * @param username the username address of the user to load
     * @return the UserDetails representing the loaded user account
     * @throws UsernameNotFoundException if no user with the specified email address is found
     */
    @Override
    public Account loadUserByUsername(String username) {
        Optional<Account> account = loadUser(null, username);
        return account
            .orElseThrow(() -> new UsernameNotFoundException(String.format(username,
                AuthenticationConstants.MESSAGE_USERNAME_NOT_FOUND)));
    }

    /**
     * Finds an account by its id.
     *
     * @param id the id to search for
     * @return an Optional containing the account if found, or an empty Optional otherwise
     */
    public Optional<Account> findById(final Integer id) {
        return accountRepository.findById(id);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public void deleteById(final Integer id) {
        accountRepository.deleteById(id);
    }

    public ResponseEntity<Account> updateAccount(final Integer id, final RegisterRequest request) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
