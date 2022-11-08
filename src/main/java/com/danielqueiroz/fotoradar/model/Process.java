package com.danielqueiroz.fotoradar.model;

import lombok.*;
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

//    @OneToMany
//    @JoinColumn(name = "page_id")
    private Collection<Page> pages;

//    @OneToOne
//    @JoinColumn(name = "attorney_id")
    private Attorney attorney;

}
