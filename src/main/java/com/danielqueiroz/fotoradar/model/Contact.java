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

    @DBRef
    private Company company;

}