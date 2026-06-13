package com.InsRem.service.impl;

import com.InsRem.entity.Agent;
import com.InsRem.repository.AgentRepository;
import com.InsRem.service.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {

    private final AgentRepository agentRepository;

    @Override
    public Agent getLoggedInAgent() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        return agentRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Agent not found"));
    }

    @Override
    public void uploadProfileImage(
            MultipartFile file)

            throws Exception {

        Agent agent =
                getLoggedInAgent();

        String filename =
                System.currentTimeMillis()
                        + "_"
                        + file.getOriginalFilename();

        Path path =
                Paths.get(
                        "uploads/profile/"
                                + filename);

        Files.write(
                path,
                file.getBytes());

        agent.setProfileImage(
                filename);

        agentRepository.save(agent);
    }
}