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
    public AccountsController(AccountsService AccountsService) {this.AccountsService = AccountsService;}

    @PostMapping
    public void save(@RequestBody Accounts Accounts){AccountsService.save(Accounts);}

    @GetMapping("{id}")
    public Accounts searchById(@PathVariable Integer id){return AccountsService.searchById(id);}

    @GetMapping
    public List<Accounts> search(){return AccountsService.searchAll();}

    @GetMapping("{id}/exist")
    public boolean existById(@PathVariable Integer id){return AccountsService.existsByid(id);
    }
}
