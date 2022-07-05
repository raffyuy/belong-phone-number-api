package com.belong.phonenumber.controller;

import com.belong.phonenumber.model.Customer;
import com.belong.phonenumber.model.PhoneNumber;
import com.belong.phonenumber.repository.CustomerRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Get all phone numbers belonging to a customer
     * @param id - The customer id
     * @return A list of phone numbers belonging to the customer, or a 404 not found if the customer record is not found
     */
    @Operation(summary = "Get all phone numbers belonging to a customer given a customer Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the customer",
                    content = { @Content(mediaType = "application/json",
                        array = @ArraySchema(schema = @Schema(implementation = PhoneNumber.class))) }),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content) })
    @GetMapping("/customers/{id}/phonenumbers")
    ResponseEntity<?> getPhoneNumbersByCustomerId(@PathVariable Long id) {
        Optional<Customer> customerById = customerRepository.findById(id);
        return customerById.map(result -> ResponseEntity.ok().body(result.getPhoneNumbers()))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
