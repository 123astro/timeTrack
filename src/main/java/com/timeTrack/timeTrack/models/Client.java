package com.timeTrack.timeTrack.models;

import javax.persistence.*;
import java.util.Set;

@Entity

public class Client {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String role;  // plaintiff or defendant

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private Set<Matter> matter;

    public Client() {
    }

    public Client(String name, String email, String role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Set<Matter> getMatter() {
        return matter;
    }

    public void setMatter(Set<Matter> matter) {
        this.matter = matter;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
