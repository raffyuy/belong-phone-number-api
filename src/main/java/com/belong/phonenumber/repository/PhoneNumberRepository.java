package com.belong.phonenumber.repository;

import com.belong.phonenumber.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, String> {
    List<PhoneNumber> findAllByCustomerId(Long customerId);

    @Modifying
    @Query("update PhoneNumber p set p.isActive = ?1 where p.phoneNumber = ?2")
    void setIsActiveForPhoneNumber(Boolean isActive, String phoneNumber);
}
