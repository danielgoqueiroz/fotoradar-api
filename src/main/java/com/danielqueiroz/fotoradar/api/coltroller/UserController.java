package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.model.Role;
import com.danielqueiroz.fotoradar.model.RoleToUserForm;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/users/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveUser(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtosave")
    public ResponseEntity addToUser(@RequestBody RoleToUserForm roleToUserForm) {
        userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRole());
        return ResponseEntity.ok().build();
    }

}

