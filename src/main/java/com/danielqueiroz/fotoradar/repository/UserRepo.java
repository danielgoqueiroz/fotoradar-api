package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<User, String> {
    User findUserByUsername(String username);
}
