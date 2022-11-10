package com.danielqueiroz.fotoradar.model;

import lombok.*;
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
public class Process {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String processNumber;
    private Date processDate;

    @DBRef
    private Collection<Page> pages;

    @DBRef
    private Attorney attorney;

}
