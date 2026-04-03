package com.api_search.project.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "alert")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email")
    private String email;

    @Column(name = "leak")
    private String leak;

    @Column(name = "risk_level")
    private String risk_level;

    @Column(name = "date_alert")
    private LocalDateTime date_alert;

    // getters and setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLeak() {
        return leak;
    }

    public void setLeak(String leak) {
        this.leak = leak;
    }

    public String getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(String risk_level) {
        this.risk_level = risk_level;
    }

    public LocalDateTime getDate_alert() {
        return date_alert;
    }

    public void setDate_alert(LocalDateTime date_alert) {
        this.date_alert = date_alert;
    }

}
