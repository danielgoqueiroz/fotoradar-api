package com.danielqueiroz.fotoradar.repository;

import com.danielqueiroz.fotoradar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
