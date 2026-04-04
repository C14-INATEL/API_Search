package com.api_search.project.repository;

import com.api_search.project.entity.Accounts;
import com.api_search.project.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Integer>{
    void deleteByUserId(Integer userId);

    List<Alert> findByUserId(Integer userId);

    boolean existsByUserId(Integer userId);
}
