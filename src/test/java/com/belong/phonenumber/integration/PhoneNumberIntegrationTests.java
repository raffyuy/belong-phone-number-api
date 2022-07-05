package com.belong.phonenumber.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PhoneNumberIntegrationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getPhoneNumbersReturnsListOfNumbers() throws Exception {
        mvc.perform(get("/api/v1/phonenumbers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].phoneNumber").value("0491570006"))
                .andExpect(jsonPath("$[0].isActive").value(false))
                .andExpect(jsonPath("$[10].phoneNumber").value("0491571804"));
    }

    @Test
    public void activateReturnsActivatedPhoneNumberRecord() throws Exception {
        mvc.perform(patch("/api/v1/phonenumbers/0491570006/activate").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.phoneNumber").value("0491570006"))
                .andExpect(jsonPath("$.isActive").value(true));
    }


    @Test
    public void activateReturnsErrorOnNonexistentNumber() throws Exception {
        mvc.perform(patch("/api/v1/phonenumbers/12345/activate").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
