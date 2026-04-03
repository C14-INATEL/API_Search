package com.api_search.project.repository;

import com.api_search.project.entity.Leakeds_email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Leakeds_emailRepository extends JpaRepository<Leakeds_email, Integer> {
}
