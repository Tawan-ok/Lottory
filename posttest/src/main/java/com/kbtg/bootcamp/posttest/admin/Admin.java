package com.kbtg.bootcamp.posttest.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("admin_id")
    @Column(name = "admin_id")
    private Long admin_id;
    @JsonProperty("username")
    @Column(name = "username")
    private String username;
    @JsonProperty("password")
    @Column(name = "password")
    private String password;
    @JsonProperty("email")
    @Column(name = "email")
    private String email;

    public Long getId() {
        return admin_id;
    }

    public void setId(Long admin_id) {
        this.admin_id = admin_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
