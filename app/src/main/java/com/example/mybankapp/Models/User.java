package com.example.mybankapp.Models;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String name;
    private String balance;

    public User() {
    }

    public User( String name,String email, String balance) {
        this.email = email;
        this.name = name;
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
