package com.rudyah.contactapi.contact;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


class ContactServiceTest {

    @Mock private ContactRepository contactRepository;
    private AutoCloseable autoCloseable;
    private ContactServiceImpl underTest;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ContactServiceImpl(contactRepository);
    }

    @AfterEach
    void teardown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getContactsWithoutSearchKeyword() {
        // when
        underTest.getContacts(null);

        //then
        verify(contactRepository).findAll();
    }

    @Test
    void getContactsWithSearchKeyword() {
        // given
        String keyword = "Imogen";
        // when
        underTest.getContacts(keyword);

        //then
        verify(contactRepository).search(keyword);
    }

    @Test
    @Disabled
    void addContact() {
    }

    @Test
    @Disabled
    void deleteContact() {
    }

    @Test
    @Disabled
    void updateContact() {
    }

    @Test
    @Disabled
    void getContactById() {
    }
}