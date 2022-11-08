package com.danielqueiroz.fotoradar.api.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
@Document
@Getter
public class RoleToUserFormDTO {
    private String username;
    private String role;
}