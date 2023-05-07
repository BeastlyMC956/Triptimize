package com.beastlymc.triptimize.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * Finds a user account by its id.
     *
     * @param id the id to search for
     * @return an Optional containing the user account if found, or an empty Optional otherwise
     */
    Optional<Account> findById(int id);

    /**
     * Finds a user account by its email address.
     *
     * @param email the email address to search for
     * @return an Optional containing the user account if found, or an empty Optional otherwise
     */
    Optional<Account> findByEmail(String email);
    
}
