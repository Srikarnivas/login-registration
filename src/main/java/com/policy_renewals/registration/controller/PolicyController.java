package com.policy_renewals.registration.controller;

import com.policy_renewals.registration.DTO.PolicyDetailsDTO;
import com.policy_renewals.registration.model.Policy;
import com.policy_renewals.registration.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    @GetMapping("/details/{transactionId}")
    public ResponseEntity<PolicyDetailsDTO> getPolicyDetails(@PathVariable Long transactionId) {
        PolicyDetailsDTO details = policyService.getPolicyDetails(transactionId);
        return ResponseEntity.ok(details);
    }

    @GetMapping
    public ResponseEntity<Map<Long, Policy>> getAllPolicies() {
        Map<Long, Policy> policyMap = policyService.getAllPoliciesAsMap();
        return ResponseEntity.ok(policyMap);
    }

    @PutMapping("/update/{transactionId}")
    public ResponseEntity<PolicyDetailsDTO> updatePolicyBrought(@PathVariable Long transactionId) {
        PolicyDetailsDTO updatedPolicy = policyService.updatePolicyBrought(transactionId);
        return ResponseEntity.ok(updatedPolicy);
    }
}

