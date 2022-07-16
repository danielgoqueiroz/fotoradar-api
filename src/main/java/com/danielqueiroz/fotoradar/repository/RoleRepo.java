package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
        Role findRoleByName(String name);
}
