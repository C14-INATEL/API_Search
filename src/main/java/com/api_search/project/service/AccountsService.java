package com.api_search.project.service;

import com.api_search.project.entity.Accounts;
import com.api_search.project.entity.Alert;
import com.api_search.project.repository.AccountsRepository;
import org.mindrot.jbcrypt.BCrypt;
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
        String password;
        String hash;

        password = accounts.getPassword_hash();
        hash = BCrypt.hashpw(password, BCrypt.gensalt());
        accounts.setPassword_hash(hash);
        accountsRepository.save(accounts);
    }

    public Accounts saveObject(Accounts accounts) {
        String password;
        String hash;

        password = accounts.getPassword_hash();
        hash = BCrypt.hashpw(password, BCrypt.gensalt());
        accounts.setPassword_hash(hash);
        return accountsRepository.save(accounts);
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

    public void deleteByid(Integer id){
        accountsRepository.deleteById(id);
    }

    public void deleteALL(){
        accountsRepository.deleteAll();
    }
}