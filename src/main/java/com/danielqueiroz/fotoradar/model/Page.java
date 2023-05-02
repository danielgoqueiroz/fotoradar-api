package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Page {

    @Id
    private String id;

    @DBRef
    private User user;

    private String url;

    private Image image;

    private Company company;

    @DBRef
    private Process process;

    private LocalDateTime createdAt = LocalDateTime.now();

}
