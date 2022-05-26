package com.danielqueiroz.fotoradar.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String name;
    private String email;
    private String cpf;
    private String username;
    private String password;

}
