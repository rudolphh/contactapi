package com.rudyah.contactapi.contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    //@Query("SELECT c FROM Contact c WHERE c.email = ?1")
    Optional<Contact> findContactByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT c FROM Contact c WHERE CONCAT(c.firstName, ' ', c.lastName, ' ', c.email) LIKE %?1%")
    List<Contact> search(String keyword);
}
