package com.belong.phonenumber.controller;

import com.belong.phonenumber.model.Customer;
import com.belong.phonenumber.model.PhoneNumber;
import com.belong.phonenumber.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTests {

    @InjectMocks
    CustomerController customerController;

    @Mock
    CustomerRepository customerRepository;

    @Test
    public void getCustomerPhoneNumbersReturnsList() throws Exception {
        //GIVEN
        Long customerId = 1L;
        List<PhoneNumber> phoneNumbers = List.of(
                PhoneNumber.builder().phoneNumber("0491570006").isActive(true).build(),
                PhoneNumber.builder().phoneNumber("0491570156").isActive(true).build(),
                PhoneNumber.builder().phoneNumber("0491570157").isActive(true).build()
        );
        Customer customer = Customer.builder().id(1L).phoneNumbers(phoneNumbers).build();
        when(customerRepository.findById(customerId)).thenReturn(Optional.ofNullable(customer));

        //WHEN
        ResponseEntity<?> response = customerController.getPhoneNumbersByCustomerId(customerId);

        //THEN
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(phoneNumbers, response.getBody());
    }

    @Test
    public void getCustomerPhoneNumbersReturns404WhenNoCustomerFound() throws Exception {
        //GIVEN
        Long customerId = 1L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        //WHEN
        ResponseEntity<?> response = customerController.getPhoneNumbersByCustomerId(customerId);

        //THEN
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
