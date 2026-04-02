package com.api_search.project.controller;

import com.api_search.project.entity.Accounts;
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

    @PutMapping("/{id}/update_email")
    public Accounts updateEmail(@PathVariable Integer id, @RequestBody Accounts account){
        Accounts email_update = accountsService.searchById(id);
        email_update.setAddress(account.getAddress());
        return accountsService.saveAccount(account);
    }

    @PutMapping("/{id}/update_description")
    public Accounts updateDescription(@PathVariable Integer id, @RequestBody Accounts account){
        Accounts description_update = accountsService.searchById(id);
        description_update.setDescription(account.getDescription());
        return accountsService.saveAccount(account);
    }

    @PutMapping("/{id}/update_password")
    public Accounts updatePassword(@PathVariable Integer id, @RequestBody Accounts account){
        Accounts password_update = accountsService.searchById(id);
        password_update.setPassword_hash(account.getPassword_hash());
        return accountsService.saveAccount(account);
    }


}
