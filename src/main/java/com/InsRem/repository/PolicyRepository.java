package com.InsRem.repository;

import com.InsRem.entity.Agent;
import com.InsRem.entity.Policy;
import com.InsRem.entity.PolicyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    List<Policy> findByPolicyNumberContainingIgnoreCase(
            String policyNumber);

    List<Policy> findByCustomerNameContainingIgnoreCase(String customerName);

    List<Policy> findByCompanyNameContainingIgnoreCase(String companyName);

    List<Policy> findByExpiryDateBefore(LocalDate date);

    List<Policy> findByAgent(Agent agent);

    List<Policy> findByExpiryDate(LocalDate expiryDate);

    List<Policy> findByStatus(PolicyStatus status);

    long countByAgent(Agent agent);

    long countByAgentAndStatus(Agent agent, PolicyStatus status);

    boolean existsByPolicyNumber(
            String policyNumber);
}