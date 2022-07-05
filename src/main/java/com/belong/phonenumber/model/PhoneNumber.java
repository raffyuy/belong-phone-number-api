package com.belong.phonenumber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "phonenumber")
public class PhoneNumber {

    @Id
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customerId")
    @JsonIgnore
    private Customer customer;

    private Boolean isActive;
}
