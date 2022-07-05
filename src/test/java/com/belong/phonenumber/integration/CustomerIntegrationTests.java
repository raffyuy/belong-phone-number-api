package com.belong.phonenumber.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getCustomerPhoneNumbersReturnsListOfNumbers() throws Exception {
        mvc.perform(get("/api/v1/customers/1/phonenumbers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].phoneNumber").value("0491570006"))
                .andExpect(jsonPath("$[0].isActive").value(false));
    }

    @Test
    public void getCustomerPhoneNumbersReturnsErrorOnNonexistentCustomerId() throws Exception {
        mvc.perform(get("/api/v1/customers/999/phonenumbers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
