package com.danielqueiroz.fotoradar.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Attorney {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private String id;

//    @OneToOne
    private User user;

    private String oabNumber;

//    @OneToMany
    private Collection<Process> processes;

}
