package com.InsRem.service.impl;

import com.InsRem.dto.LoginRequest;
import com.InsRem.dto.RegisterRequest;
import com.InsRem.entity.Agent;
import com.InsRem.repository.AgentRepository;
import com.InsRem.service.AgentService;
import com.InsRem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AgentService agentService;
    private final AgentRepository agentRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Agent register(RegisterRequest request) {

        if (agentRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        Agent agent = Agent.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .mobile(request.getMobile())
                .build();

        return agentRepository.save(agent);
    }

    @Override
    public String login(LoginRequest request) {

        Agent agent = agentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                agent.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        return "Login Successful";
    }

    @Override
    public void changePassword(
            String oldPassword,
            String newPassword) {

        Agent agent =
                agentService.getLoggedInAgent();

        if (!passwordEncoder.matches(
                oldPassword,
                agent.getPassword())) {

            throw new RuntimeException(
                    "Old Password Wrong");
        }

        agent.setPassword(
                passwordEncoder.encode(
                        newPassword));

        agentRepository.save(agent);
    }
}