package com.danielqueiroz.fotoradar.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("user")
@Getter
public class CreateUserDTO {

    private String name;
    private String email;
    private String cpf;
    private String username;
    private String password;

}
