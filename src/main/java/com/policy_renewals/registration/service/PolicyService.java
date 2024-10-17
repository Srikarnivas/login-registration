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
        dto.setPolicyStatus(policiesBrought.getStatus());  // Now status comes from PoliciesBrought

        return dto;
    }

    public PolicyDetailsDTO updatePolicyBrought(Long transactionId) {
        PoliciesBrought policiesBrought = policiesBroughtRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        Policy policy = policyRepository.findById(policiesBrought.getPolicyId())
                .orElseThrow(() -> new RuntimeException("Policy not found"));

        // Update fields from Policies_Table
        policiesBrought.setPrice(policy.getPrice());
        policiesBrought.setPeriod(policy.getPeriod());
        policiesBrought.setBroughtDate(new Date()); // Set brought date to current date

        // Set status to "Active"
        policiesBrought.setStatus("Active");

        // Save updated PoliciesBrought record
        policiesBroughtRepository.save(policiesBrought);

        // Return updated details
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

    // Method to fetch all policies from PoliciesBrought table
    public List<PoliciesBrought> getAllPoliciesBrought() {
        return policiesBroughtRepository.findAll();
    }
}
