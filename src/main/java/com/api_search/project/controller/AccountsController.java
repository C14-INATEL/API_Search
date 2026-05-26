package com.api_search.project.controller;

import com.api_search.project.client.FindByEmailClient;
import com.api_search.project.entity.Accounts;
import com.api_search.project.excepetion.AccountsExcept;
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
    public void save(@RequestBody Accounts Accounts) throws AccountsExcept {
        try {
            accountsService.save(Accounts);
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public Accounts searchById(@PathVariable Integer id) throws AccountsExcept{
        try {
            return accountsService.searchById(id);
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
        }

    @GetMapping
    public List<Accounts> search() throws AccountsExcept{
        try {
            return accountsService.searchAll();
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public List<Accounts> searchAccountsByUserId(@PathVariable Integer userId) throws AccountsExcept {
        try {
            return accountsService.searchAccountsByUser(userId);
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
    }
    @GetMapping("{id}/exist")
    public boolean existById(@PathVariable Integer id) throws AccountsExcept{
        try {
            return accountsService.existsByid(id);
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteaccountByID(@PathVariable Integer id) throws AccountsExcept{
        try {
            accountsService.deleteByid(id);
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
    }

    @DeleteMapping("user/{userId}")
    public void deleteAllUserAccounts(@PathVariable Integer userId) throws AccountsExcept{
        try {
            accountsService.deleteAllUserAccounts(userId);
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
        }

    @DeleteMapping
    public void deleteALL() throws AccountsExcept{
        try {
            accountsService.deleteALL();
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
    }

    @PostMapping("/accountMonitored/{userId}/{email}")
    public ResponseEntity <String> accountMonitored(@PathVariable Integer userId, @PathVariable String email) throws AccountsExcept {
        try {
            findByEmailClient
                    .findByEmailResponseFlux(email)
                    .doOnNext(dto -> accountsService.saveFromResponseEmailWebClientHIBP(dto, email, userId))
                    .subscribe();
            return ResponseEntity.ok("Account added successfully");
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
    }
}
