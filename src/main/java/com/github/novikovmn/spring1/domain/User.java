package com.github.novikovmn.spring1.domain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private Integer enabled;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Authority> authorities = new ArrayList<>();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getUsername() { return this.username; }
    public String getPassword() { return this.password; }
    public Integer getEnabled() { return this.enabled; }
    public List<Authority> getAuthorities() { return this.authorities; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = new BCryptPasswordEncoder().encode(password); }
    public void setEnabled(Integer enabled) { this.enabled = enabled; }
    public void setAuthorities(List<Authority> authorities) { this.authorities = authorities; }
}
