package com.policy_renewals.registration.service;

import com.policy_renewals.registration.DTO.PolicyDetailsDTO;
import com.policy_renewals.registration.model.PoliciesBrought;
import com.policy_renewals.registration.model.Policy;
import com.policy_renewals.registration.repository.PoliciesBroughtRepository;
import com.policy_renewals.registration.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PolicyService {

    @Autowired
    private PoliciesBroughtRepository policiesBroughtRepository;

    @Autowired
    private PolicyRepository policyRepository;

    public PolicyDetailsDTO getPolicyDetails(Long transactionId) {
        PoliciesBrought policiesBrought = policiesBroughtRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        Policy policy = policyRepository.findById(policiesBrought.getPolicyId())
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        PolicyDetailsDTO dto = new PolicyDetailsDTO();
        dto.setBroughtPrice(policiesBrought.getPrice());
        dto.setBroughtDate(policiesBrought.getBroughtDate());
        dto.setBroughtPeriod(policiesBrought.getPeriod());
        dto.setHolderName(policiesBrought.getHolderName());
        dto.setHolderAddress(policiesBrought.getHolderAddress());
        dto.setEmail(policiesBrought.getEmail());
        dto.setPolicyName(policy.getPolicyName());
        dto.setPolicyPrice(policy.getPrice());
        dto.setPolicyPeriod(policy.getPeriod());
        dto.setDescription(policy.getDescription());
        dto.setPolicyStatus(policiesBrought.getStatus());
        dto.setRenewalDate(policiesBrought.getRenewalDate());  // Corrected to use setter

        return dto;
    }

    public PolicyDetailsDTO updatePolicyBrought(Long transactionId) {
        PoliciesBrought policiesBrought = policiesBroughtRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        Policy policy = policyRepository.findById(policiesBrought.getPolicyId())
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        // Update fields from PoliciesBrought
        policiesBrought.setPrice(policy.getPrice());
        policiesBrought.setPeriod(policy.getPeriod());

        // Check if this is a new policy or a renewal
        if (policiesBrought.getBroughtDate() == null) {
            policiesBrought.setBroughtDate(new Date());  // Set brought date for new policies
        } else {
            policiesBrought.setRenewalDate(new Date());  // Set renewal date for existing policies
        }

        policiesBrought.setStatus("Active");

        policiesBroughtRepository.save(policiesBrought);

        return getPolicyDetails(transactionId);
    }

    public Map<Long, Policy> getAllPoliciesAsMap() {
        List<Policy> policies = policyRepository.findAll();
        Map<Long, Policy> policyMap = new HashMap<>();

        for (Policy policy : policies) {
            policyMap.put(policy.getPolicyId(), policy);
        }

        return policyMap;
    }

    public List<PoliciesBrought> getAllPoliciesBrought() {
        return policiesBroughtRepository.findAll();
    }

    public String getPolicyNameById(Long policyId) {
        return policyRepository.findPolicyNameById(policyId);
    }
}
