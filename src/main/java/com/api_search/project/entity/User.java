package com.api_search.project.entity;
import java.time.LocalDateTime;
import java.util.Date;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String token;

    @Column(name = "email_to_in")
    private String email;

    @Column(name = "password_to_in")
    private String password;

    @Column(name = "date_to_in")
    private LocalDateTime date;

    @Column(name = "date_update_sign")
    private LocalDateTime date_update;

    // getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate_update() {
        return date_update;
    }

    public void setDate_update(LocalDateTime date_update) {
        this.date_update = date_update;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
