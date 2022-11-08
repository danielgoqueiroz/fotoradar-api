package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepo extends MongoRepository<Role, String> {
        Role findRoleByName(String name);
}
