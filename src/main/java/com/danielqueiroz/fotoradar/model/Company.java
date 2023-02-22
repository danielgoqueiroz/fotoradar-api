package com.danielqueiroz.fotoradar.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document
public class Company {

    @Id
    private String id;

    private String name;
    private String cnpj;
    private String email;
    private String phone;
    private String address;
    private String host;

    @DBRef
    private Contact responsable;

    @DBRef
    @Builder.Default
    private Collection<Contact> contacts = new ArrayList<>();

}