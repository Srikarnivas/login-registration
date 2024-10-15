package com.policy_renewals.registration.service;

import com.policy_renewals.registration.model.Agent;
import com.policy_renewals.registration.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Agent registerAgent(Agent agent) {
        // Encrypt the password
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        return agentRepository.save(agent);
    }

    public Optional<Agent> loginAgent(String email, String password) {
        Optional<Agent> agent = agentRepository.findByEmail(email);
        if (agent.isPresent() && passwordEncoder.matches(password, agent.get().getPassword())) {
            return agent;
        }
        return Optional.empty();
    }
}
