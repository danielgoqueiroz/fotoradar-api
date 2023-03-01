package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends MongoRepository<Role, String> {
        Role findRoleByName(String name);
}
