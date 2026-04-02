package com.api_search.project.entity;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "leak")
public class Leak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_monitored")
    private String account_monitored;

    @Column(name = "date_ocurrence")
    private Date date_ocureence;

    @Column(name = "register")
    private BigInteger register;

    // getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getRegister() {
        return register;
    }

    public void setRegister(BigInteger register) {
        this.register = register;
    }

    public Date getDate_ocureence() {
        return date_ocureence;
    }

    public void setDate_ocureence(Date date_ocureence) {
        this.date_ocureence = date_ocureence;
    }

    public String getAccount_monitored() {
        return account_monitored;
    }

    public void setAccount_monitored(String account_monitored) {
        this.account_monitored = account_monitored;
    }


}
