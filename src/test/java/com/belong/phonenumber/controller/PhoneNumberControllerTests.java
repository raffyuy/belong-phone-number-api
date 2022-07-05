package com.belong.phonenumber.controller;

import com.belong.phonenumber.model.Customer;
import com.belong.phonenumber.model.PhoneNumber;
import com.belong.phonenumber.repository.PhoneNumberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PhoneNumberControllerTests {

    @InjectMocks
    PhoneNumberController phoneNumberController;

    @Mock
    PhoneNumberRepository phoneNumberRepository;

    @Test
    public void getAllPhoneNumbers() throws Exception {
        //GIVEN
        Long customerId = 1L;
        List<PhoneNumber> phoneNumbers = List.of(
                PhoneNumber.builder().phoneNumber("0491570006").isActive(true).build(),
                PhoneNumber.builder().phoneNumber("0491570156").isActive(true).build(),
                PhoneNumber.builder().phoneNumber("0491570157").isActive(true).build()
        );
        Customer customer = Customer.builder().id(1L).phoneNumbers(phoneNumbers).build();
        when(phoneNumberRepository.findAll(any(Sort.class))).thenReturn(phoneNumbers);

        //WHEN
        List<PhoneNumber> result = phoneNumberController.phoneNumbers();

        //THEN
        assertEquals(phoneNumbers, result);
    }

    @Test
    public void activatePhoneNumberReturnsActivatedRecord() throws Exception {
        //GIVEN1
        String number = "0491570006";
        PhoneNumber phoneNumber = PhoneNumber.builder().phoneNumber(number).isActive(false).build();
        when(phoneNumberRepository.findById(number)).thenReturn(Optional.of(phoneNumber));

        //WHEN
        ResponseEntity<?> response = phoneNumberController.activatePhoneNumber(number);

        //THEN
        verify(phoneNumberRepository, times(1)).findById(number);
        verify(phoneNumberRepository, times(1)).save(any());
        assertTrue(phoneNumber.getIsActive());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(phoneNumber, response.getBody());
    }

    @Test
    public void activatePhoneNumberReturnsNotFoundStatusCode() throws Exception {
        //GIVEN1
        String number = "12345";

        when(phoneNumberRepository.findById(number)).thenReturn(Optional.empty());

        //WHEN
        ResponseEntity<?> response = phoneNumberController.activatePhoneNumber(number);

        //THEN
        verify(phoneNumberRepository, times(1)).findById(number);
        verify(phoneNumberRepository, times(0)).save(any());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
