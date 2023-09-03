package com.beastlymc.triptimize.repository;

import com.beastlymc.triptimize.model.account.Account;
import com.beastlymc.triptimize.model.account.Verification;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface VerificationRepository extends JpaRepository<Verification, Integer> {

    Optional<Verification> findByAccount(Account account);

    List<Verification> findAllByExpirationDateBefore(Timestamp now);
}
