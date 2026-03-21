package com.CSC340.MinervasList.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false)
    private String name;

    public Customer() {
    }

    public Customer(String name, String email, String password) {
        super(email, password);
        this.name = name;
    }

    public Customer(Long id, String name, String email, String password) {
        super(email, password);
        this.customerId = id;
        this.name = name;
    }

    public Long getId() {
        return customerId;
    }

    public void setId(Long id) {
        this.customerId = id;
    }

    public String getName() {
        return name;
    }

    public void getName(String name) {
        this.name = name;
    }

}
