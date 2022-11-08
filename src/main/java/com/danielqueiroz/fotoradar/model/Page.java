package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Page {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String url;

//    @ManyToOne
    @JsonManagedReference
    private Image image;

//    @ManyToOne
    @JsonManagedReference
    private Company company;

//    @ManyToOne
    @JsonManagedReference
    private Process process;

}
