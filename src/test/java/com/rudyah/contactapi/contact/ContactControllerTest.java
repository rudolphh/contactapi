package com.rudyah.contactapi.contact;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void getContacts() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/v1/contacts");
        MvcResult result = mvc.perform(request).andReturn();
        //Assertions.assertI();
    }

    @Test
    void getContact() {
    }

    @Test
    void addNewContact() {
    }

    @Test
    void deleteContact() {
    }

    @Test
    void updateContact() {
    }
}