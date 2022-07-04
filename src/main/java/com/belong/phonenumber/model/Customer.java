package com.belong.phonenumber.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    private Long id;

    @OneToMany(mappedBy="customer", fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JsonIgnore
    private List<PhoneNumber> phoneNumbers;

}
