package com.InsRem.service.impl;

import com.InsRem.entity.Agent;
import com.InsRem.entity.PolicyStatus;
import com.InsRem.repository.PolicyRepository;
import com.InsRem.service.AgentService;
import com.InsRem.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final PolicyRepository policyRepository;
    private final AgentService agentService;

    @Override
    public long totalPolicies() {

        Agent agent = agentService.getLoggedInAgent();

        return policyRepository.countByAgent(agent);
    }

    @Override
    public long activePolicies() {

        Agent agent = agentService.getLoggedInAgent();

        return policyRepository.countByAgentAndStatus(
                agent,
                PolicyStatus.ACTIVE
        );
    }

    @Override
    public long expiredPolicies() {

        Agent agent = agentService.getLoggedInAgent();

        return policyRepository.countByAgentAndStatus(
                agent,
                PolicyStatus.EXPIRED
        );
    }

    @Override
    public long renewedPolicies() {

        Agent agent = agentService.getLoggedInAgent();

        return policyRepository.countByAgentAndStatus(
                agent,
                PolicyStatus.RENEWED
        );
    }

    @Override
    public long expiringSoon() {

        Agent agent = agentService.getLoggedInAgent();

        return policyRepository.findByAgent(agent)
                .stream()
                .filter(policy ->
                        policy.getExpiryDate()
                                .equals(LocalDate.now().plusDays(2)))
                .count();
    }
}