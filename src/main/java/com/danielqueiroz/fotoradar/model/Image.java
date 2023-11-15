package com.danielqueiroz.fotoradar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.time.LocalDateTime.now;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@Setter
@Data
public class Image {

    @Id
    private String id;
    @DBRef
    private User user;
    private String name;
    private String description;

    private Date date;

    private String link;

    @JsonIgnore
    private String blob;

}
