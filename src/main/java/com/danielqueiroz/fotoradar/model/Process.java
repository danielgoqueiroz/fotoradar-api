package com.danielqueiroz.fotoradar.model;

import com.danielqueiroz.fotoradar.service.PageSevice;
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

    @DBRef
    private Collection<Page> pages = Collections.emptyList();

    @DBRef
    private Collection<Attorney> attorney = Collections.emptyList();

    @DBRef
    private Collection<Payment> payments = Collections.emptyList();

}