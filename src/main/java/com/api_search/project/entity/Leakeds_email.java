package com.api_search.project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "leakeds_email")
public class Leakeds_email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_leak")
    private int id_leakd;

    @Column(name = "email_id")
    private String email_id;

    // Constructor
    public Leakeds_email(Integer id, int id_leakd, String email_id) {
        this.id = id;
        this.id_leakd = id_leakd;
        this.email_id = email_id;
    }

    public Leakeds_email() {
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId_leakd() {
        return id_leakd;
    }

    public void setId_leakd(int id_leakd) {
        this.id_leakd = id_leakd;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }
}
