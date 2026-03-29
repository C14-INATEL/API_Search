package com.api_search.project.service;

import com.api_search.project.entity.Accounts;
import com.api_search.project.repository.AccountsRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountsService {
    private AccountsRepository accountsRepository;

    // Construtor
    public AccountsService(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public void save(Accounts accounts){
        accountsRepository.save(accounts);
    }

    public Accounts searchById(Integer id){
        return accountsRepository.findById(id).orElse(null);
    }

    public List<Accounts> searchAll(){
        return accountsRepository.findAll();
    }

    public boolean existsByid(Integer id) {
        return accountsRepository.existsById(id);
    }
}