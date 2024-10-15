package com.policy_renewals.registration.repository;

import com.policy_renewals.registration.model.PoliciesBrought;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliciesBroughtRepository extends JpaRepository<PoliciesBrought, Long> {
}
