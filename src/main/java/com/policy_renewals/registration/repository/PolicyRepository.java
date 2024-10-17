package com.policy_renewals.registration.repository;

import com.policy_renewals.registration.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
    @Query("SELECT p.policyName FROM Policy p WHERE p.policyId = :policyId") // Change Policies to Policy
    String findPolicyNameById(@Param("policyId") Long policyId);
}
