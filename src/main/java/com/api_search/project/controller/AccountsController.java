package com.api_search.project.controller;

import com.api_search.project.entity.Accounts;
import com.api_search.project.service.AccountsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountsController {
    private AccountsService accountsService;

    // Construtor

    public AccountsController(AccountsService accountsService){
        this.accountsService = accountsService;
    }

    @PostMapping
    public void save(@RequestBody Accounts Accounts) {
        accountsService.save(Accounts);}

    @GetMapping("{id}")
    public Accounts searchById(@PathVariable Integer id) {
        return accountsService.searchById(id);
    }

    @GetMapping
    public List<Accounts> search() {
        return accountsService.searchAll();
    }

    @GetMapping("{id}/exist" )
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

    @PutMapping(value="/{id}")
    public Accounts update(@PathVariable Integer id, @RequestBody Accounts accounts_before){
        Accounts account_update = accountsService.searchById(id);
        account_update.setAddress(accounts_before.getAddress());
        account_update.setDescription(accounts_before.getDescription());
        account_update.setStatus(accounts_before.getStatus());
        account_update.setPassword_hash(accounts_before.getPassword_hash());

        return accountsService.saveAccount(account_update);
    }


}
