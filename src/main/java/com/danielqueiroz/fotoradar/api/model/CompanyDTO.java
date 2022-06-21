package com.danielqueiroz.fotoradar.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDTO {

    private Long id;

    private String name;
    private String cnpj;
    private String suidResponsable;
    private String phone;
    private String address;
    private String host;
    private String mail;

}
