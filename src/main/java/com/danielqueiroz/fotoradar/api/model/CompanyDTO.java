package com.danielqueiroz.fotoradar.api.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
@Getter
public class CompanyDTO {

    private String id;

    private String name;
    private String cnpj;
    private String suidResponsable;
    private String phone;
    private String address;
    private String host;
    private String mail;

}
