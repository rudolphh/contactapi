package com.rudyah.contactapi.contact;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ContactConfig {

    @Bean
    CommandLineRunner commandLineRunner(ContactRepository contactRepository){
        return args -> {
            Contact imogen = new Contact("Imogen", "Sage", "imisage@gmail.com");
            Contact noah = new Contact("Noah", "Tyler", "noahtyler@gmail.com");
            Contact sam = new Contact("Sammi", "May", "sammimay@gmail.com");
            contactRepository.saveAll(
                    List.of(imogen, noah, sam)
            );
        };
    }
}
