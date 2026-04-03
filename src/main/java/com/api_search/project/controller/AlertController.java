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

    @GetMapping("/user/{user_id}")
    public List<Alert> searchAccountByUser(@PathVariable Integer user_id){
        return alertService.searchAccountsByUser(user_id);
    }

    @GetMapping
    public List<Alert> search(){
        return alertService.searchAll();
    }

    @GetMapping("/{id}/exist")
    public boolean existById(@PathVariable Integer id){
        return alertService.existsByid(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        alertService.deleteByid(id);
    }

    @DeleteMapping
    public void deleteALL(){
        alertService.deleteALL();
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
