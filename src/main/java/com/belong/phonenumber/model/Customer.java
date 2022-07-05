package com.belong.phonenumber.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private Long id;

    @OneToMany(mappedBy="customer", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<PhoneNumber> phoneNumbers;

}
