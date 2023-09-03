package com.beastlymc.triptimize.jobs;

import com.beastlymc.triptimize.model.account.Verification;
import com.beastlymc.triptimize.repository.AccountRepository;
import com.beastlymc.triptimize.repository.VerificationRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VerificationJob implements Job {

    private AccountRepository accountRepository;
    private VerificationRepository verificationRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        verificationRepository.findAllByExpirationDateBefore(now).stream()
            .map(Verification::getAccount)
            .forEach(account -> {
                if (account == null) {
                    return;
                }
                account.setVerification(null);
                accountRepository.save(account);
            });
        verificationRepository.deleteAll(verificationRepository.findAllByExpirationDateBefore(now));
    }
}
