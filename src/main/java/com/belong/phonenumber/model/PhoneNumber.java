package com.belong.phonenumber.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import javax.persistence.*;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "phonenumber")
public class PhoneNumber {

    @Id
    private String phoneNumber;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customerId")
    private Customer customer;

    private Boolean isActive;
}
