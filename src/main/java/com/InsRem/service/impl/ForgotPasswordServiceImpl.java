package com.InsRem.service.impl;

import com.InsRem.entity.Agent;
import com.InsRem.repository.AgentRepository;
import com.InsRem.service.EmailService;
import com.InsRem.service.ForgotPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ForgotPasswordServiceImpl
        implements ForgotPasswordService {

    private final AgentRepository repository;

    private final EmailService emailService;

    private final PasswordEncoder encoder;

    @Override
    public void sendResetLink(
            String email) {

        Agent agent = repository
                .findByEmail(email)
                .orElseThrow();

        String token =
                UUID.randomUUID().toString();

        agent.setResetToken(token);

        agent.setTokenExpiry(
                LocalDateTime.now()
                        .plusMinutes(30));

        repository.save(agent);

        String link =
                "http://localhost:8080/reset-password?token="
                        + token;

        emailService.sendEmail(
                email,
                "Reset Password",
                link
        );
    }

    @Override
    public void resetPassword(
            String token,
            String password) {

        Agent agent = repository
                .findByResetToken(token)
                .orElseThrow();

        if(agent.getTokenExpiry()
                .isBefore(
                        LocalDateTime.now())) {

            throw new RuntimeException(
                    "Token Expired");
        }

        agent.setPassword(
                encoder.encode(password));

        agent.setResetToken(null);

        agent.setTokenExpiry(null);

        repository.save(agent);
    }
}