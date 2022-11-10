package com.danielqueiroz.fotoradar.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Attorney {

    @Id
    private String id;

    @DBRef
    private User user;

    private String oabNumber;

    @DBRef
    private Collection<Process> processes;

}
