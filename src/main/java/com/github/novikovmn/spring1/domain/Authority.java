package com.github.novikovmn.spring1.domain;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "authority")
    private String authority;

    public Authority() {}
    public Authority(User user, String authority) {
        this.user = user;
        this.authority = authority;
    }

    public User getUser() { return this.user; }
    public String getAuthority() { return this.authority; }

    public void setUser(User user) { this.user = user; }
    public void setAuthority(String authority) { this.authority = authority; }
}
