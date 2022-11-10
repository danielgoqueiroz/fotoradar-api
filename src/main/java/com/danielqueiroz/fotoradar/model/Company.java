package com.danielqueiroz.fotoradar.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

//@Entity
//@Table(name = "company")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Company {

    @Id
    private String id;

    private String name;
    private String cnpj;
    private String suidResponsable;
    private String nameResponsable;
    private String phone;
    private String address;
    private String host;
    private String mail;

    @DBRef
    private Collection<Contact> contacts = new ArrayList<>();

    @DBRef
    private Collection<Page> pages =  new ArrayList<>();

}