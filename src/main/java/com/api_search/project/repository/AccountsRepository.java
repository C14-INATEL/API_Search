package com.api_search.project.repository;

import com.api_search.project.entity.Accounts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Integer>{
    List<Accounts> findByUserId(Integer userId);

    void deleteByUserId(Integer userId);

    void deleteByUserIdAndEmailMonitored(Integer userId, String emailMonitored);

    @Modifying
    @Transactional
    @Query("DELETE FROM Accounts ac where ac.emailMonitored = :email")
    void deleteByEmail(@Param("email") String email);

}