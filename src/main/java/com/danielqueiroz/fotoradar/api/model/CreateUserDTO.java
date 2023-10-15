package com.danielqueiroz.fotoradar.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("user")
@Getter
@JsonInclude(NON_NULL)
public class CreateUserDTO {

    private String name;
    private String email;
    private String cpf;
    private String username;
    private String password;

}
