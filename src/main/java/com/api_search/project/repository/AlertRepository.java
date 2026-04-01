package com.api_search.project.repository;

import com.api_search.project.entity.Accounts;
import com.api_search.project.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Integer>{
}
