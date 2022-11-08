package com.danielqueiroz.fotoradar.model;

import lombok.*;
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

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String name;
    private String cnpj;
    private String suidResponsable;
    private String nameResponsable;
    private String phone;
    private String address;
    private String host;
    private String mail;

//    @OneToMany
//    @JoinColumn(name = "contact_id")
    private Collection<Contact> contacts = new ArrayList<>();

//    @OneToMany
//    @JoinColumn(name = "page_id")
    private Collection<Page> pages =  new ArrayList<>();

}