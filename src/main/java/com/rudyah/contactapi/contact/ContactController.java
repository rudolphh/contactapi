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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/contacts")
@Log4j2
public class ContactController {

    public final ContactService contactService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ContactData>>> getContacts(@RequestParam(required = false) String keyword) {

        List<ContactData> contacts = contactService.getContacts(keyword);

        String message;
        if (keyword != null) {
            log.info("search conducted");
            message = "Contact search results";
        } else {
            log.info("get all contacts");
            message = "All contacts";
        }

        ApiResponse<List<ContactData>> apiResponse = new ApiResponse<>(true, HttpStatus.OK,
                message, contacts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping(path = "{contactId}")
    public ResponseEntity<ApiResponse<Contact>> getContact(@PathVariable("contactId") Long contactId) {

        Contact contact = contactService.getContactById(contactId);

        ApiResponse<Contact> apiResponse = new ApiResponse<>(true, HttpStatus.OK,
                "Contact with id: " + contactId + " returned", contact);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Contact>> addNewContact(@Validated(OnInsert.class) @RequestBody Contact contact) {
        Contact savedContact = contactService.addContact(contact);
        log.info("add new contact");

        ApiResponse<Contact> apiResponse = new ApiResponse<>(true, HttpStatus.OK,
                "Contact added successfully", savedContact);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedContact.getId()).toUri();
        return ResponseEntity.created(location).body(apiResponse);
        //new ResponseEntity<>(apiResponse, HttpStatus.OK);

    }

    @DeleteMapping(path = "{contactId}")
    public ResponseEntity<ApiResponse<?>> deleteContact(@PathVariable("contactId") Long contactId) {

        contactService.deleteContact(contactId);
        log.info("delete contact");

        ApiResponse<Object> apiResponse = new ApiResponse<>(
                true, HttpStatus.OK,
                "Contact with id: " + contactId + " deleted successfully");

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping(path = "{contactId}")
    public ResponseEntity<ApiResponse<Contact>> updateContact(@PathVariable("contactId") Long contactId,
                                                              @Validated(OnUpdate.class) @RequestBody(required = false) Contact contact) {

        Contact updatedContact = contactService.updateContact(contactId, contact);
        log.info("update contact");

        ApiResponse<Contact> apiResponse = new ApiResponse<>(
                true, HttpStatus.OK,
                "Contact with id: " + contactId + " successfully updated", updatedContact);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
