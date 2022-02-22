package com.rudyah.contactapi.contact;

import com.rudyah.contactapi.validation.groups.OnInsert;
import com.rudyah.contactapi.validation.groups.OnUpdate;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "first name is mandatory", groups = OnInsert.class)
    @Size(min = 3, message = "First name must be at least three characters", groups = {OnInsert.class, OnUpdate.class})
    private String firstName;

    @NotBlank(message = "last name is mandatory", groups = OnInsert.class)
    @Size(min = 3, message = "Last name must be at least three characters", groups = {OnInsert.class, OnUpdate.class})
    private String lastName;

    @NotBlank(message = "email is mandatory", groups = OnInsert.class)
    @Email(groups = {OnInsert.class, OnUpdate.class})
    @Size(min = 5, message = "Email must be at least five characters", groups = {OnInsert.class, OnUpdate.class})
    private String email;

    public Contact(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
