package com.ben.reviews.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;


@Builder
@Entity (name = "user")
public class User implements UserDetails {
    //@SequenceGenerator(name="user_id_seq",sequenceName = "user_id_seq",allocationSize = 1)
    //@GeneratedValue(strategy = AUTO, generator = "user_id_seq")
    @GeneratedValue(strategy = IDENTITY)
    @Id
    private Integer id;
    private String name;
    private String password;

    @Transient
    private final String role = "user";

    public User(){}
    public User(Integer id, String username, String password){
        this.id = id;
        this.name = username;
        this.password = password;
    }

    public void setName(String username){
        this.name = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // TODO: maybe use only the override getter for name
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new java.util.ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(this.role));
        return simpleGrantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
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
