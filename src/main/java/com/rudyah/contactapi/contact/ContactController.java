package com.rudyah.contactapi.contact;

import com.rudyah.contactapi.response.models.ApiResponse;
import com.rudyah.contactapi.validation.groups.OnInsert;
import com.rudyah.contactapi.validation.groups.OnUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/contacts")
@Log4j2
public class ContactController {

    public final ContactService contactService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Contact>>> getContacts() {
         List<Contact> contacts = contactService.getContacts();
         log.info("get all contacts");

         ApiResponse<List<Contact>> apiResponse = new ApiResponse<>(true, HttpStatus.OK,
                        "All contacts", contacts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Contact>> addNewContact(@Validated(OnInsert.class) @RequestBody Contact contact) {
        Contact savedContact = contactService.addContact(contact);

        ApiResponse<Contact> apiResponse = new ApiResponse<>(true, HttpStatus.OK,
                "Contact added successfully", savedContact);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @DeleteMapping(path = "{contactId}")
    public ResponseEntity<ApiResponse<?>> deleteContact(@PathVariable("contactId") Long contactId){

        contactService.deleteContact(contactId);

        ApiResponse<Object> apiResponse = new ApiResponse<>(
                true, HttpStatus.OK,
                "Contact with id: " + contactId + " deleted successfully");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping(path = "{contactId}")
    public ResponseEntity<ApiResponse<Contact>> updateContact(@PathVariable("contactId") Long contactId,
                                                              @Validated(OnUpdate.class) @RequestBody(required = false) Contact contact) {

        Contact updatedContact = contactService.updateContact(contactId, contact);

        ApiResponse<Contact> apiResponse = new ApiResponse<>(
                true, HttpStatus.OK,
                "Contact with id: " + contactId + " successfully updated", updatedContact);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
