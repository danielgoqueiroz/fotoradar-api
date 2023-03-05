package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Document(collection = "process")
public class Process {

    @Id
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
