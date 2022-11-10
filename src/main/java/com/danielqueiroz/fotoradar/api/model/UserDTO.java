package com.danielqueiroz.fotoradar.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("user")
@Getter
public class UserDTO {

    private String name;
    private String email;
    @JsonIgnore
    private String cpf;
    private String username;
    @JsonIgnore
    private String password;

}
