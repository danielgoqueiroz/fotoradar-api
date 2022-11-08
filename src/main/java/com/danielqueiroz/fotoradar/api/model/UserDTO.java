package com.danielqueiroz.fotoradar.api.model;

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
    private String cpf;
    private String username;
    private String password;

}
