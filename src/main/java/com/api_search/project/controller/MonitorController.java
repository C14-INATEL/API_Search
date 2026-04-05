package com.api_search.project.controller;

import com.api_search.project.orchestrator.MonitorOrchestrator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitor")
public class MonitorController {

    private final MonitorOrchestrator orchestrator;

    public MonitorController(MonitorOrchestrator orchestrator) {
        this.orchestrator = orchestrator;
    }

    @PostMapping
    public String test(@RequestParam Integer userId, @RequestParam String email, @RequestParam String accountMonitored, @RequestParam String password_hash, @RequestParam String risk_status) {
        orchestrator.execute(userId, email, accountMonitored, password_hash, risk_status);
        return "Monitor executed!";
    }
}