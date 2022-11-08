package com.danielqueiroz.fotoradar.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@Setter

public class Contact {

//    @Id
//    @Column(nullable = false)
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

//    @ManyToOne
//    @JoinColumn(name = "company_id")
    private Company company;

}