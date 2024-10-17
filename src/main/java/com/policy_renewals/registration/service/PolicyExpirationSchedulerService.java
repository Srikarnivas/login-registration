package com.policy_renewals.registration.service;

import com.policy_renewals.registration.model.PoliciesBrought;
import com.policy_renewals.registration.repository.PoliciesBroughtRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PolicyExpirationSchedulerService {

    private static final Logger logger = LoggerFactory.getLogger(PolicyExpirationSchedulerService.class);

    @Autowired
    private PoliciesBroughtRepository policiesBroughtRepository; // Use the correct repository

    @Scheduled(cron = "0 25 11 * * ?") // Runs every day at 11:20 AM
    public void checkExpiredPolicies() {
        logger.info("Checking for expired policies..."); // Log the execution

        List<PoliciesBrought> policies = policiesBroughtRepository.findAll(); // Fetch all policies

        for (PoliciesBrought policy : policies) {
            if (isPolicyExpired(policy)) {
                logger.info("Updating policy ID {} to expired.", policy.getPolicyId());
                policy.setStatus("Expired"); // Update status to "Expired"
                policiesBroughtRepository.save(policy); // Save the updated policy
            }
        }
    }

    private boolean isPolicyExpired(PoliciesBrought policy) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(policy.getBroughtDate());
        cal.add(Calendar.MONTH, policy.getPeriod()); // Add the period in months to the brought date
        Date renewalDate = cal.getTime();

        // Check if today's date is after the calculated renewal date
        return new Date().after(renewalDate);
    }
}
