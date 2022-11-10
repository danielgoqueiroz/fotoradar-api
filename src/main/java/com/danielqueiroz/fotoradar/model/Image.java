package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@Setter
public class Image {


    @Id
    private String id;
    private String name;
    private String description;
    private Date date;
    private String link;

    @DBRef
    private User user;

    @JsonIgnore
    private String blob;

    @DBRef
    private Collection<Page> pages;

}
