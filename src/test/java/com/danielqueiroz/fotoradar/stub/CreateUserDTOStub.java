package com.danielqueiroz.fotoradar.stub;

import com.danielqueiroz.fotoradar.api.model.CreateUserDTO;
import com.danielqueiroz.fotoradar.model.User;
import com.google.gson.Gson;

public class CreateUserDTOStub {

    private static final Gson gson = new Gson();

    public static CreateUserDTO getCreateUserDTOStubFromUser(User userDTO) {
        return CreateUserDTO.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .cpf(userDTO.getCpf())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
    }

    public static String getCreateUserJsonDTOStubFromUser(User userDTO) {
        return gson.toJson(getCreateUserDTOStubFromUser(userDTO));
    }
}
