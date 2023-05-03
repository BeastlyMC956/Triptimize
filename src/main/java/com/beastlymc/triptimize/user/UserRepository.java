package com.beastlymc.triptimize.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * A repository for managing user accounts in the system.
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Finds a user account by its email address.
     *
     * @param email the email address to search for
     * @return an Optional containing the user account if found, or an empty Optional otherwise
     */
    Optional<User> findByEmail(String email);

}
