package com.policy_renewals.registration.service;

import com.policy_renewals.registration.model.PoliciesBrought;
import com.policy_renewals.registration.repository.PoliciesBroughtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PolicyEmailService {

    @Autowired
    private PoliciesBroughtRepository policiesBroughtRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PolicyService policyService;

    public void sendEmailsForExpiredPolicies() {
        List<PoliciesBrought> policies = policiesBroughtRepository.findAll();

        for (PoliciesBrought policy : policies) {
            if (isPolicyExpired(policy)) {
                sendExpirationEmail(policy);
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

    private void sendExpirationEmail(PoliciesBrought policy) {
        String subject = "Policy Expiration Notification!";

        // Calculate the expiration date
        Calendar cal = Calendar.getInstance();
        cal.setTime(policy.getBroughtDate());
        cal.add(Calendar.MONTH, policy.getPeriod());
        Date expirationDate = cal.getTime();

        // Retrieve the policy name using the PolicyService
        String policyName = policyService.getPolicyNameById(policy.getPolicyId());

        String body = String.format(
                "Dear %s,\n\n"
                        + "We hope this message finds you well! This is a notification that your policy (ID: %d ) for %s "
                        + "has expired on %tB %<te, %<tY. \n\n"
                        + "Please contact your agent for further assistance in renewing or reinstating your policy. \n\n"
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
