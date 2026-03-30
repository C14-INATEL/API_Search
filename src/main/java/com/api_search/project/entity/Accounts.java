package com.api_search.project.entity;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

@Entity
@Table(name = "accounts_monitored")
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer account_ID;

    @Column(name = "email_monitored")
    private String address;

    @Column(name = "account_monitored")
    private String description;

    @Column(name = "risk_status")
    private String status;

    @Column(name = "hash_password")
    private String password_hash;

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccount_ID() {
        return account_ID;
    }

    public void setAccount_ID(Integer account_ID) {
        this.account_ID = account_ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
}
