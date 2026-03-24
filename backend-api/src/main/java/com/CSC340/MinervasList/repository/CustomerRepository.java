package com.CSC340.MinervasList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CSC340.MinervasList.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}