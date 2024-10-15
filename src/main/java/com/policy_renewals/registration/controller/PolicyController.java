package com.policy_renewals.registration.controller;

import com.policy_renewals.registration.DTO.PolicyDetailsDTO;
import com.policy_renewals.registration.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

