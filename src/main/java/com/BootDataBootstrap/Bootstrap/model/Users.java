package com.BootDataBootstrap.Bootstrap.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity(name = "users")
public class Users {

    public Users(Users users){
        this.active = users.getActive();
        this.email = users.getEmail();
        this.role = users.getRole();
        this.name = users.getName();
        this.lastname = users.getLastname();
        this.userId = users.getUserId();
        this.password = users.getPassword();
        this.enabled = users.isEnabled();
        this.confirmationToken=users.getConfirmationToken();
    }

    public Users(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_ID")
    private int userId;
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    private String email;
    @Basic
    @Column(name = "password")
    private String password;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "lastname")
    private String lastname;
    @Basic
    @Column(name = "role")
    private String role;
    @Basic
    @Column(name = "active")
    private Integer active;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "confirmationToken")
    private String confirmationToken;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }


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
                Objects.equals(active, users.active) &&
                Objects.equals(enabled, users.enabled) &&
                Objects.equals(confirmationToken, users.confirmationToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, name, lastname, role, active, enabled, confirmationToken);
    }
}
