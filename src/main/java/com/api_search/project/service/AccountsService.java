package com.api_search.project.service;

import com.api_search.project.entity.Accounts;
import com.api_search.project.repository.AccountsRepository;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
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

    public List<Accounts> searchAccountsByUser(Integer user_id){
        return accountsRepository.findByUserId(user_id);
    }

    @Transactional
    public void deleteAllUserAccounts(Integer user_id){
        accountsRepository.deleteByUserId(user_id);
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