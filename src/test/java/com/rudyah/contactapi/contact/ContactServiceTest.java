package com.rudyah.contactapi.contact;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock private ContactRepository contactRepository;
    @Mock private ContactEntityConverter contactEntityConverter;
    private ContactServiceImpl underTest;

    @BeforeEach
    void setup() {
        underTest = new ContactServiceImpl(contactRepository, contactEntityConverter);
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
        ArgumentCaptor<String> keywordArgumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(contactRepository).search(keywordArgumentCaptor.capture());

        String capturedKeyword = keywordArgumentCaptor.getValue();
        Assertions.assertThat(capturedKeyword).isEqualTo(keyword);
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