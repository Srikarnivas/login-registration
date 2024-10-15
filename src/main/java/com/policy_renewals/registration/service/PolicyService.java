package com.policy_renewals.registration.service;

import com.policy_renewals.registration.DTO.PolicyDetailsDTO;
import com.policy_renewals.registration.model.PoliciesBrought;
import com.policy_renewals.registration.model.Policy;
import com.policy_renewals.registration.repository.PoliciesBroughtRepository;
import com.policy_renewals.registration.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {

    @Autowired
    private PoliciesBroughtRepository policiesBroughtRepository;

    @Autowired
    private PolicyRepository policyRepository;

    public PolicyDetailsDTO getPolicyDetails(Long transactionId) {
        // Fetch PoliciesBrought by transactionId
        PoliciesBrought policiesBrought = policiesBroughtRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        // Fetch the related policy using policyId
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

        return dto;
    }
}

