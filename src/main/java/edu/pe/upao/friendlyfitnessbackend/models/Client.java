package edu.pe.upao.friendlyfitnessbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client implements UserDetails {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientID;
    @Column(name = "firs_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private int age;
    @Column(name = "cell_phone")
    private String cell;
    @Column(name = "tastes")
    private String tastes;
    @Column(name = "expectations")
    private String expectations;
    @Column(name = "preferences")
    private String preferences;

    public void setClientID(Long clientID) {
        this.clientID = clientID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public void setTastes(String tastes) {
        this.tastes = tastes;
    }

    public void setExpectations(String expectations) {
        this.expectations = expectations;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}