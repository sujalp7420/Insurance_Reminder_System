package com.InsRem.security;

import com.InsRem.entity.Agent;
import com.InsRem.repository.AgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgentDetailsService implements UserDetailsService {

    private final AgentRepository agentRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Agent agent = agentRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Agent Not Found"));

        return User.builder()
                .username(agent.getEmail())
                .password(agent.getPassword())
                .authorities("AGENT")
                .build();
    }
}