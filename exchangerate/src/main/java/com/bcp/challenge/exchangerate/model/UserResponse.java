package com.bcp.challenge.exchangerate.model;

import java.util.HashSet;
import java.util.Set;

public class UserResponse {

    private Long id;
    private String username;
    private String firstname;
    private String lastName;

    private Set<String> roles = new HashSet<>();

    public UserResponse(Long id, String username, String firstname, String lastName, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastName = lastName;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
