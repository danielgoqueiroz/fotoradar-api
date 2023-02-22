package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
    User findUserByUsername(String username);
}
