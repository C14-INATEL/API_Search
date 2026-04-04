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

    @GetMapping("/user/{userId}")
    public List<Accounts> searchAccountsByUserId(@PathVariable Integer userId){
        return accountsService.searchAccountsByUser(userId);
    }

    @GetMapping("{id}/exist")
    public boolean existById(@PathVariable Integer id){
        return accountsService.existsByid(id);
    }

    @DeleteMapping("/{id}")
    public void deleteaccountByID(@PathVariable Integer id){
        accountsService.deleteByid(id);
    }

    @DeleteMapping("user/{userId}")
    public void deleteAllUserAccounts(@PathVariable Integer userId){
        accountsService.deleteAllUserAccounts(userId);
    }

    @DeleteMapping
    public void deleteALL(){
        accountsService.deleteALL();
    }

    @PutMapping("/{id}/update_adress")
    public Accounts updateEmail(@PathVariable Integer id, @RequestBody Accounts accounts) {
        Accounts accounts_update = accountsService.searchById(id);
        accounts_update.setAddress(accounts.getAddress());
        return accountsService.saveObject(accounts_update);
    }

    @PutMapping("/{id}/update_description")
    public Accounts updateAccount(@PathVariable Integer id, @RequestBody Accounts accounts) {
        Accounts accounts_update = accountsService.searchById(id);
        accounts_update.setDescription(accounts.getDescription());
        return accountsService.saveObject(accounts_update);
    }

    @PutMapping("/{id}/update_status")
    public Accounts updateRiskStatus(@PathVariable Integer id, @RequestBody Accounts accounts) {
        Accounts accounts_update = accountsService.searchById(id);
        accounts_update.setStatus(accounts.getStatus());
        return accountsService.saveObject(accounts_update);
    }

    @PutMapping("/{id}/update_password")
    public Accounts updateRiskPassword(@PathVariable Integer id, @RequestBody Accounts accounts) {
        Accounts accounts_update = accountsService.searchById(id);
        accounts_update.setPassword_hash(accounts.getPassword_hash());
        return accountsService.saveObject(accounts_update);
    }
}
