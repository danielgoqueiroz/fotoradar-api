package com.danielqueiroz.fotoradar.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@CompoundIndex(name =  "id_host_idx",def = "{'user._id': 1, 'host': -1}")
public class Company {

    @Id
    private String id;
    @DBRef
    private User user;
    private String name;
    private String cnpj;
    private String email;
    private String phone;
    private String address;

    @Indexed(name = "host_idx")
    private String host;

    @DBRef
    private Contact responsable;

    @DBRef
    @Builder.Default
    private Collection<Contact> contacts = new ArrayList<>();

}