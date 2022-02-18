package com.rudyah.contactapi.contact;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ContactRepositoryTest {

    @Autowired
    private ContactRepository underTestRepository;

    @AfterEach
    void teardown(){
        underTestRepository.deleteAll();
    }

    @Test
    void itShouldCheckIfContactEmailExists() {

        // given
        String email = "rudolpharthur@gmail.com";
        Contact contact =  new Contact("Rudy", "Hernandez", email);
        underTestRepository.save(contact);

        // when
        boolean expected = underTestRepository.existsByEmail(email);

        //then
        Assertions.assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfContactEmailDoesNotExists() {

        // given
        String email = "rudolpharthur@gmail.com";
        Contact contact =  new Contact("Rudy", "Hernandez", "samayoral@gmail.com");
        underTestRepository.save(contact);

        // when
        boolean expected = underTestRepository.existsByEmail(email);

        //then
        Assertions.assertThat(expected).isFalse();
    }
}