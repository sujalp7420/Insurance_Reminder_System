package com.InsRem.repository;

import com.InsRem.entity.Agent;
import com.InsRem.entity.PolicyStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long> {

    Optional<Agent> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<Agent> findByResetToken(
            String token
    );

    long countByStatus(
            PolicyStatus status);

    long countByCompanyName(
            String companyName);
}