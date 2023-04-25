package com.danielqueiroz.fotoradar.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@Setter
public class Contact {

    @Id
    private String id;

    private String name;
    private String phone;
    private String email;
    private String cpf;
    private String rg;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;

}