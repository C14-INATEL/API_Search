package com.api_search.project.orchestrator;

import com.api_search.project.entity.Accounts;
import com.api_search.project.entity.Alert;
import com.api_search.project.entity.User;
import com.api_search.project.service.AccountsService;
import com.api_search.project.service.AlertService;
import com.api_search.project.service.LeakService;
import com.api_search.project.service.UserService;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class MonitorOrchestrator {

    private final LeakService leakService;
    private final AccountsService accountsService;
    private final AlertService alertService;
    private final UserService userService;

    public MonitorOrchestrator(LeakService leakService, AccountsService accountsService, AlertService alertService, UserService userService
    ) {
        this.leakService = leakService;
        this.accountsService = accountsService;
        this.alertService = alertService;
        this.userService = userService;
    }
    public void execute(Integer userId, String email, String account_monitored, String password_hash, String risk_status) {

        User user = userService.searchById(userId);

        Accounts account = new Accounts();
        account.setUserId(userId);
        account.setAddress(email);
        account.setDescription(account_monitored);
        account.setPassword_hash(password_hash);
        account.setStatus(risk_status);

        accountsService.save(account);
        System.out.println("Call leak service");

        leakService.searchAndSave(email);

        Alert alert = new Alert();
        alert.setEmail(email);
        alert.setRisk_level("SAFE");
        alert.setDate_alert(LocalDateTime.now());
        alert.setUserId(userId);

        alertService.save(alert);
    }
}