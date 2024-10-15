package com.policy_renewals.registration.controller;

import com.policy_renewals.registration.model.Agent;
import com.policy_renewals.registration.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    private AgentService agentService;

    @PostMapping("/register")
    public ResponseEntity<String> registerAgent(@RequestBody Agent agent) {
        agentService.registerAgent(agent);
        return ResponseEntity.ok("Agent registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAgent(@RequestParam String email, @RequestParam String password) {
        return agentService.loginAgent(email, password)
                .map(agent -> ResponseEntity.ok("Login successful!"))
                .orElse(ResponseEntity.status(401).body("Invalid credentials!"));
    }
}
