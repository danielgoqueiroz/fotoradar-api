package com.danielqueiroz.fotoradar.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@Getter
public class ImageDTO {

    private String id;
    private String link;
    private String name;
    private String description;

}
