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
    @Builder.Default
    private List<Page> pages = new ArrayList<>();

    @Builder.Default
    private List<Attorney> attorney = new ArrayList<>();

    @Builder.Default
    private List<Payment> payments = new ArrayList<>();

}
