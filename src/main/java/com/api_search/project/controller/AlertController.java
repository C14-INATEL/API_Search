package com.api_search.project.controller;


import com.api_search.project.entity.Alert;
import com.api_search.project.entity.User;
import com.api_search.project.service.AlertService;
import com.api_search.project.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alert")
public class AlertController {
    private AlertService alertService;

    //constructor
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping
    public void save(@RequestBody Alert alert){
        alertService.save(alert);
    }

    @GetMapping("/{id}")
    public Alert searchAccountById(@PathVariable Integer id){
        return alertService.searchAccountById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Alert> searchAccountsByUser(@PathVariable Integer userId){
        return alertService.searchAccountsByUser(userId);
    }

    @GetMapping
    public List<Alert> search(){
        return alertService.searchAll();
    }

    @GetMapping("/{id}/exist")
    public boolean AccountexistById(@PathVariable Integer id){
        return alertService.existsByid(id);
    }

    @GetMapping("/user/{userId}/exist")
    public boolean UserexistById(@PathVariable Integer userId){
        return alertService.UserexistsByid(userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        alertService.deleteByid(id);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteAllUserAccounts(@PathVariable Integer userId){
        alertService.deleteAllUserAccounts(userId);
    }

    @PutMapping("/{id}/update_account_email")
    public Alert updateEmail(@PathVariable Integer id, @RequestBody Alert alert) {
        Alert alert_update = alertService.searchAccountById(id);
        alert_update.setEmail(alert.getEmail());
        return alertService.saveObject(alert_update);
    }

    @PutMapping("/{id}/update_account_leak")
    public Alert updateLeak(@PathVariable Integer id, @RequestBody Alert alert) {
        Alert alert_update = alertService.searchAccountById(id);
        alert_update.setLeak(alert.getLeak());
        return alertService.saveObject(alert_update);
    }

    @PutMapping("/{id}/update_account_risk_level")
    public Alert updateRiskLevel(@PathVariable Integer id, @RequestBody Alert alert) {
        Alert alert_update = alertService.searchAccountById(id);
        alert_update.setRisk_level(alert.getRisk_level());
        return alertService.saveObject(alert_update);
    }
}
