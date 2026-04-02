package com.api_search.project.controller;

import com.api_search.project.entity.Accounts;
import com.api_search.project.entity.Alert;
import com.api_search.project.service.AccountsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountsController{
    private AccountsService accountsService;

    // Construtor

    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @PostMapping
    public void save(@RequestBody Accounts Accounts){
        accountsService.save(Accounts);}

    @GetMapping("{id}")
    public Accounts searchById(@PathVariable Integer id){
        return accountsService.searchById(id);
    }

    @GetMapping
    public List<Accounts> search(){
        return accountsService.searchAll();
    }

    @GetMapping("{id}/exist")
    public boolean existById(@PathVariable Integer id){
        return accountsService.existsByid(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        accountsService.deleteByid(id);
    }

    @DeleteMapping
    public void deleteALL(){
        accountsService.deleteALL();
    }
    @PutMapping("/{user_id}/update_email")
    public Accounts updateEmail(@PathVariable Integer id, @RequestBody Accounts accounts) {
        |Accounts alert_update = alertService.searchById(id);
        alert_update.setEmail(alert.getEmail());
        return alertService.saveObject(alert_update);
    }

    @PutMapping("/{user_id}/update_leak")
    public Alert updateLeak(@PathVariable Integer id, @RequestBody Alert alert) {
        Alert alert_update = alertService.searchById(id);
        alert_update.setLeak(alert.getLeak());
        return alertService.saveObject(alert_update);
    }

    @PutMapping("/{user_id}/risk_level")
    public Alert updateRiskLevel(@PathVariable Integer id, @RequestBody Alert alert) {
        Alert alert_update = alertService.searchById(id);
        alert_update.setRisk_level(alert.getRisk_level());
        return alertService.saveObject(alert_update);
    }
}
