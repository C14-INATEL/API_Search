package com.api_search.project.repository;

import com.api_search.project.entity.Accounts;
import com.api_search.project.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Integer>{
    List<Accounts> findByUserId(Integer userId);

    void deleteByUserId(Integer userId);
}