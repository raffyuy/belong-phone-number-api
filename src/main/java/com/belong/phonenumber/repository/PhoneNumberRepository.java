package com.belong.phonenumber.repository;

import com.belong.phonenumber.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, String> {
    List<PhoneNumber> findAllByCustomerId(Long customerId);

    @Modifying
    @Query("update PhoneNumber p set p.isActive = ?2 where p.phoneNumber = ?1")
    int setIsActiveForPhoneNumber(String phoneNumber, Boolean isActive);
}
