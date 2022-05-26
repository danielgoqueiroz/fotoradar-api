package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.api.model.ErrorMessage;
import com.danielqueiroz.fotoradar.api.model.UserDTO;
import com.danielqueiroz.fotoradar.exception.AlreadyExistException;
import com.danielqueiroz.fotoradar.exception.ValidationException;
import com.danielqueiroz.fotoradar.model.Role;
import com.danielqueiroz.fotoradar.model.RoleToUserForm;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.service.UserService;
import com.danielqueiroz.fotoradar.service.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping(value = "/user/save", consumes = APPLICATION_JSON_VALUE,  produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/save").toUriString());
        try {
            return ResponseEntity.created(uri).body(
                    userService.saveUser(
                            User
                                    .builder()
                                    .username(user.getUsername())
                                    .password(user.getPassword())
                                    .name(user.getName())
                                    .cpf(user.getCpf())
                                    .email(user.getEmail())
                                    .build()
                    )
            );
        } catch (AlreadyExistException | ValidationException e) {
            String message = e.getMessage();
            return ResponseEntity.badRequest().body(new ErrorMessage(this.getClass().getName(), message));
        }
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtosave")
    public ResponseEntity addToUser(@RequestBody RoleToUserForm roleToUserForm) {
        userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRole());
        return ResponseEntity.ok().build();
    }

}

