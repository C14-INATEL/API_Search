package com.api_search.project.service;

import com.api_search.project.entity.Accounts;
import com.api_search.project.excepetion.AccountsExcept;
import com.api_search.project.repository.AccountsRepository;
import com.api_search.project.response.FindByEmailResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
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
    public void deleteAllUserAccounts(Integer user_id) throws AccountsExcept {
        try {
            accountsRepository.deleteByUserId(user_id);
        } catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
    }

    public Accounts searchById(Integer id) throws AccountsExcept{
        try {
            return accountsRepository.findById(id).orElse(null);
        }catch (Exception e){
            throw new AccountsExcept(e.getMessage());
        }
    }

    public List<Accounts> searchAll() throws AccountsExcept{
        try{
            return accountsRepository.findAll();
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
    }

    public boolean existsByid(Integer id) throws AccountsExcept {
        try{
            return accountsRepository.existsById(id);
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
    }

    public void deleteByid(Integer id) throws AccountsExcept{
        try{
            accountsRepository.deleteById(id);
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
    }

    public void deleteALL() throws AccountsExcept{
        try
        {
            accountsRepository.deleteAll();
        }
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }
    }

    public void saveFromResponseEmailWebClientHIBP(FindByEmailResponse dto, String email, Integer userId) throws AccountsExcept{
        try
        {
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
        catch (Exception e)
        {
            throw new AccountsExcept(e.getMessage());
        }

    }
    public void saveEmailWithNoBreaches(String email, Integer userId) {
        Accounts accounts = new Accounts();
        accounts.setUserId(userId);
        accounts.setEmailMonitored(email);
        accountsRepository.save(accounts);
    }

    @Transactional
    public void deleteByUserIdAndEmail(Integer userId, String email) throws AccountsExcept {
        try {
            accountsRepository.deleteByUserIdAndEmailMonitored(userId, email);
        } catch (Exception e) {
            throw new AccountsExcept(e.getMessage());
        }
    }
}