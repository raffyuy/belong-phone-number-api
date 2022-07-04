package com.belong.phonenumber.repository;

import com.belong.phonenumber.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
