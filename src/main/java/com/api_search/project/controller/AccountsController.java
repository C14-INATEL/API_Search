package com.api_search.project.controller;

import com.api_search.project.client.FindByEmailClient;
import com.api_search.project.entity.Accounts;
import com.api_search.project.response.FindByEmailResponse;
import com.api_search.project.service.AccountsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountsController{
    private AccountsService accountsService;
    private FindByEmailClient findByEmailClient;

    // Construtor

    public AccountsController(AccountsService accountsService, FindByEmailClient findByEmailClient) {
        this.accountsService = accountsService;
        this.findByEmailClient= findByEmailClient;
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
    
    @PostMapping("/accountMonitored/{userId}/{email}")
    public ResponseEntity <String> accountMonitored(@PathVariable Integer userId, @PathVariable String email) {
        findByEmailClient
                .findByEmailResponseFlux(email)
                .doOnNext(dto -> accountsService.saveFromResponseEmailWebClientHIBP(dto,email, userId))
                .subscribe();
        return ResponseEntity.ok("Account added successfully");
    }
}
