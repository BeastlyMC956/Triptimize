package com.beastlymc.triptimize.repository;

import com.beastlymc.triptimize.model.account.Profile;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    /**
     * Finds a user account by its username.
     *
     * @param username the username to search for
     * @return an Optional containing the user account if found, or an empty Optional otherwise
     */
    Optional<Profile> findByUsername(String username);

}
