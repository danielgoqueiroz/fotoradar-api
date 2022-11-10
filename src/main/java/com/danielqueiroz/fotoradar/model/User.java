package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@Setter
public class User {

    @Id
    @JsonIgnore
    private String id;
    private String name;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String cpf;

    @JsonIgnore
    @DBRef
    private Collection<Role> roles;

    @JsonManagedReference
    @DBRef
    private Collection<Image> images;

}
