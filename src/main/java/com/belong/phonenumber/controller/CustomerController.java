package com.belong.phonenumber.controller;

import com.belong.phonenumber.model.Customer;
import com.belong.phonenumber.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Retrieve all phone numbers belonging to a customer
     * @param id - The customer id
     * @return A list of phone numbers belonging to the customer, or a 404 not found if the customer record is not found
     */
    @GetMapping("/customers/{id}/phonenumbers")
    ResponseEntity<?> getPhoneNumbersByCustomerId(@PathVariable Long id) {
        Optional<Customer> customerById = customerRepository.findById(id);
        return customerById.map(result -> ResponseEntity.ok().body(result.getPhoneNumbers()))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
