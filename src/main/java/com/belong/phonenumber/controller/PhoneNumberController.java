package com.belong.phonenumber.controller;

import com.belong.phonenumber.model.PhoneNumber;
import com.belong.phonenumber.repository.PhoneNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PhoneNumberController {

    private final Logger log = LoggerFactory.getLogger(PhoneNumberController.class);

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    /**
     * Lists all phone numbers in the database sorted by phone number
     * @return
     */
    @GetMapping("/phonenumbers")
    List<PhoneNumber> phoneNumbers() {
        return phoneNumberRepository.findAll(Sort.by(Sort.Direction.ASC, "phoneNumber"));
    }

    @PostMapping("/phonenumbers/{id}/activate")
    ResponseEntity<?> activatePhoneNumber(@PathVariable String id) {
        log.info("Updating phone number: {}", id);
        Optional<PhoneNumber> phoneNumberById = phoneNumberRepository.findById(id);
        if (phoneNumberById.isPresent()) {
            PhoneNumber phoneNumber = phoneNumberById.get();
            phoneNumber.setIsActive(true);
            phoneNumberRepository.save(phoneNumber);
            return ResponseEntity.ok().body(phoneNumber);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
