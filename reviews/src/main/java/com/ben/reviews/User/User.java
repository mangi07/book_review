package com.ben.reviews;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity (name = "user")

public class User {
    @SequenceGenerator(name="user_id_swq",sequenceName = "user_id_seq",allocationSize = 1)
    @GeneratedValue(strategy = IDENTITY, generator = "user_id_seq")
    @Id
    private Integer id;
    private String name;
    private String password;

    public User(){}
    public User(Integer id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public void setName(String usr){
        this.name = usr;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }
}
