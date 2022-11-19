package com.danielqueiroz.fotoradar.model;

import com.danielqueiroz.fotoradar.service.PageSevice;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
    @DBRef
    private Collection<Page> pages = Collections.emptyList();

    private Collection<Attorney> attorney = Collections.emptyList();

    private Collection<Payment> payments = Collections.emptyList();

}
