package com.api_search.project.repository;

import com.api_search.project.entity.Alert;
import com.api_search.project.entity.Leak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeakRepository extends JpaRepository<Leak, Integer> {
}
