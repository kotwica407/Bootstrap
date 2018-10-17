package com.BootDataBootstrap.Bootstrap.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Users {

    public Users(Users users){
        this.active = users.getActive();
        this.email = users.getEmail();
        this.role = users.getRole();
        this.name = users.getName();
        this.lastname = users.getLastname();
        this.userId = users.getUserId();
        this.password = users.getPassword();
    }

    public Users(){

    }

    private int userId;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String role;
    private Integer active;

    @Id
    @Column(name = "user_ID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "active")
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return userId == users.userId &&
                Objects.equals(email, users.email) &&
                Objects.equals(password, users.password) &&
                Objects.equals(name, users.name) &&
                Objects.equals(lastname, users.lastname) &&
                Objects.equals(role, users.role) &&
                Objects.equals(active, users.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, name, lastname, role, active);
    }
}
