package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Page {

    @Id
    private String id;

    private String url;

    @DBRef
    private Image image;

    @DBRef
    private Company company;

    @DBRef
    private Process process;

}
