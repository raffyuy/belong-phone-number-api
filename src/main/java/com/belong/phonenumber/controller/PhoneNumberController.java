package com.belong.phonenumber.controller;

import com.belong.phonenumber.model.PhoneNumber;
import com.belong.phonenumber.repository.PhoneNumberRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get all phone numbers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Phone numbers retrieved",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PhoneNumber.class))) }),
    })
    @GetMapping("/phonenumbers")
    List<PhoneNumber> phoneNumbers() {
        return phoneNumberRepository.findAll(Sort.by(Sort.Direction.ASC, "phoneNumber"));
    }

    /**
     * Given a phone number, sets the PhoneNumber's isActive status to true.
     *
     * @param id - String - an existing phone number
     * @return PhoneNumber record with updated isActive status
     */
    @Operation(summary = "Activate a phone number if it exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the phone number and activated it",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PhoneNumber.class)) }),
            @ApiResponse(responseCode = "404", description = "Phone number not found",
                    content = @Content) })
    @PatchMapping("/phonenumbers/{id}/activate")
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
