package com.policy_renewals.registration.service;

import com.policy_renewals.registration.service.PolicyService;
import com.policy_renewals.registration.model.PoliciesBrought;
import com.policy_renewals.registration.repository.PoliciesBroughtRepository;
import com.policy_renewals.registration.repository.PolicyRepository;
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
    private PoliciesBroughtRepository policiesBroughtRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkExpiredPolicies() {
        logger.info("Checking for expired policies...");

        List<PoliciesBrought> policies = policiesBroughtRepository.findAll();

        for (PoliciesBrought policy : policies) {
            if (isPolicyExpired(policy)) {
                logger.info("Updating policy ID {} to expired.", policy.getPolicyId());
                policy.setStatus("Expired");
                policiesBroughtRepository.save(policy);
                sendEmailReminder(policy);
            } else if (isOneMonthBeforeRenewal(policy)) {
                logger.info("Sending renewal reminder email for policy ID {}.", policy.getPolicyId());
                sendEmailReminder(policy);
            }
        }
    }

    private boolean isPolicyExpired(PoliciesBrought policy) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(policy.getBroughtDate());
        cal.add(Calendar.MONTH, policy.getPeriod());
        Date renewalDate = cal.getTime();
        return new Date().after(renewalDate);
    }

    private boolean isOneMonthBeforeRenewal(PoliciesBrought policy) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(policy.getBroughtDate());
        cal.add(Calendar.MONTH, policy.getPeriod() - 1);
        Date oneMonthBeforeRenewalDate = cal.getTime();
        return new Date().after(oneMonthBeforeRenewalDate) && new Date().before(new Date());
    }

    @Autowired
    private PolicyService policyService;

    private void sendEmailReminder(PoliciesBrought policy) {
        String subject = "Policy Renewal Reminder!";

        Calendar cal = Calendar.getInstance();
        cal.setTime(policy.getBroughtDate());
        cal.add(Calendar.MONTH, policy.getPeriod());
        Date expirationDate = cal.getTime();

        String policyName = policyService.getPolicyNameById(policy.getPolicyId());

        String body = String.format(
                "Dear %s,\n\n"
                        + "We hope this message finds you well! This is a friendly reminder that your policy (ID: %d ) for %s "
                        + "is set to expire on %tB %<te, %<tY. \n\n"
                        + "To avoid any interruptions in your coverage, please contact your agent at your earliest convenience "
                        + "to renew your policy before the expiration date. \n\n"
                        + "If you have any questions or need assistance, don't hesitate to reach out. "
                        + "We're here to help!\n\n"
                        + "Thank you for choosing MassMutual for your insurance needs.\n\n"
                        + "Best regards,\n"
                        + "Policy Renewals Team\n"
                        + "MassMutual Company",
                policy.getHolderName(),
                policy.getPolicyId(),
                policyName,
                expirationDate
        );

        emailService.sendEmail(policy.getEmail(), subject, body);
    }
}
