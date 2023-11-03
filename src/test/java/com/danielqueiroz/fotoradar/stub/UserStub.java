package com.danielqueiroz.fotoradar.stub;

import com.danielqueiroz.fotoradar.model.User;

public class UserStub {

    public static User getUserStub() {
        User user = new User();
        user.setUsername("teste");
        user.setPassword("senha123");
        return user;
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
