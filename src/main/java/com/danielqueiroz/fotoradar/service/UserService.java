package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.exception.AlreadyExistException;
import com.danielqueiroz.fotoradar.exception.ValidationException;
import com.danielqueiroz.fotoradar.model.Role;
import com.danielqueiroz.fotoradar.model.User;

public interface UserService {

    User saveUser(User user) throws AlreadyExistException, ValidationException;

    User updateUser(User user) throws AlreadyExistException, ValidationException;
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);

    User getCurrentUser();

}
