package com.CSC340.MinervasList.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    @Column(name = "name")
    private String name;

    public Customer() {
        super();
    }

    public Customer(String name, String email, String password) {
        super(email, password);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
