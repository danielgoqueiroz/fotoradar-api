package com.danielqueiroz.fotoradar.stub;

import com.danielqueiroz.fotoradar.model.User;

public class UserStub {

    public static User getUserStub() {
            return User.builder()
                    .id("123")
                    .username("teste")
                    .password("senha123")
                    .build();
    }
    public static User getUserUpdatedStub() {
        return User.builder()
                .username("teste")
                .password("senha123")
                .email("teste@email.com")
                .cpf("12345678901")
                .name("Teste")
                .build();
    }
}
