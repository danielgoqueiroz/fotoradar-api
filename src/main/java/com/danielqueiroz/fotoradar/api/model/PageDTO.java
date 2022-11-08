package com.danielqueiroz.fotoradar.api.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class PageDTO {

    private String imageId;
    private List<String> links;
}
