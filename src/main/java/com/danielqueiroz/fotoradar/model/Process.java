package com.danielqueiroz.fotoradar.model;

import com.danielqueiroz.fotoradar.service.PageSevice;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@Setter
public class Process {

    private String id;

    private String processNumber;
    private Date createdAt;
    private String description;
    private String link;
    private Status status;

    @JsonBackReference
    private Collection<Page> pages = new ArrayList<>();

    private Collection<Attorney> attorney = new ArrayList<>();

    private Collection<Payment> payments = new ArrayList<>();

}
