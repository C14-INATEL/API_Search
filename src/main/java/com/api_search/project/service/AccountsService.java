package com.api_search.project.service;

import com.api_search.project.entity.Accounts;
import com.api_search.project.repository.AccountsRepository;
import com.api_search.project.response.FindByEmailResponse;
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

    public void saveFromResponseEmailWebClientHIBP(FindByEmailResponse dto, String email, Integer userId){
        Accounts accounts = new Accounts();

        accounts.setUserId(userId);
        accounts.setEmailMonitored(email);
        accounts.setAddedDate(dto.getAddedDate());
        accounts.setAttribution(dto.getAttribution());
        accounts.setBreachDate(dto.getBreachDate());
        accounts.setDataClasses(dto.getDataClasses());
        accounts.setDescription(dto.getDescription());
        accounts.setDomain(dto.getDomain());
        accounts.setFabricated(dto.isFabricated());
        accounts.setMalware(dto.isMalware());
        accounts.setRetired(dto.isRetired());
        accounts.setSsensitive(dto.isSensitive());
        accounts.setSpamList(dto.isSpamList());
        accounts.setStealerLog(dto.isStealerLog());
        accounts.setSubscriptionFree(dto.isSubscriptionFree());
        accounts.setVerified(dto.isVerified());
        accounts.setLogoPath(dto.getLogoPath());
        accounts.setModifiedDate(dto.getModifiedDate());
        accounts.setNameBreaches(dto.getName());
        accounts.setPwnCount(dto.getPwnCount());
        accounts.setTitle(dto.getTitle());

        accountsRepository.save(accounts);
    }
}