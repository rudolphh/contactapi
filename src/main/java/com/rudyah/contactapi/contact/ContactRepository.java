package com.rudyah.contactapi.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    //@Query("SELECT c FROM Contact c WHERE c.email = ?1")
    Optional<Contact> findContactByEmail(String email);

    boolean existsByEmail(String email);
}
