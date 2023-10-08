package com.danielqueiroz.fotoradar.stub;

import com.danielqueiroz.fotoradar.model.User;

public class UserStub {

    public static User getUserStub() {
        User user = new User();
        user.setUsername("teste");
        user.setPassword("senha123");
        return user;
    }
}
