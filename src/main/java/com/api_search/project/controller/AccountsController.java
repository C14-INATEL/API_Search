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
}
