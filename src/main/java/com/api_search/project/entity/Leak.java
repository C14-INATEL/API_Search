package com.api_search.project.entity;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "leak")
public class Leak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_monitored")
    private String accountMonitored;

    @Column(name = "date_occurrence")
    private LocalDate date_ocurrence;

    @Column(name = "register")
    private BigInteger register;

    // getters and setters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate_ocurrence() {
        return date_ocurrence;
    }

    public void setDate_ocurrence(LocalDate date_ocurrence) {
        this.date_ocurrence = date_ocurrence;
    }

    public String getAccountMonitored() {
        return accountMonitored;
    }

    public void setAccountMonitored(String accountMonitored) {
        this.accountMonitored = accountMonitored;
    }

    public BigInteger getRegister() {
        return register;
    }

    public void setRegister(BigInteger register) {
        this.register = register;
    }
}

