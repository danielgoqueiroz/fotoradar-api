package com.danielqueiroz.fotoradar.api.controller;

import com.danielqueiroz.fotoradar.api.model.CreateUserDTO;
import com.danielqueiroz.fotoradar.api.model.ErrorMessage;
import com.danielqueiroz.fotoradar.api.model.RoleToUserFormDTO;
import com.danielqueiroz.fotoradar.api.model.UserDTO;
import com.danielqueiroz.fotoradar.exception.AlreadyExistException;
import com.danielqueiroz.fotoradar.exception.ValidationException;
import com.danielqueiroz.fotoradar.model.Role;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.service.UserServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"*"})
@Log4j2
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok().body(userService.getUser());
    }

    @PostMapping(value = "/save", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody CreateUserDTO user) {
        log.info("Save user: " + user);
        URI uri = URI.create(fromCurrentContextPath().path("/user/save").toUriString());
        try {
            return ResponseEntity.created(uri).body(
                    userService.saveUser(
                            User
                                    .builder()
                                    .username(user.getUsername())
                                    .password(user.getPassword())
                                    .roles(Collections.singleton(Role.builder().name("USER").build()))
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

    @PutMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user) {
        log.info("Update user: " + user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();

        try {
            userService.updateUser(
                    User
                            .builder()
                            .username(username)
                            .password(user.getPassword())
                            .name(user.getName())
                            .cpf(user.getCpf())
                            .email(user.getEmail())
                            .build()
            );
        } catch (ValidationException e) {
            return ResponseEntity.badRequest().body(ErrorMessage.builder().message(e.getMessage()));
        } catch (AlreadyExistException e) {
            return ResponseEntity.badRequest().body(ErrorMessage.builder().message(e.getMessage()));
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        log.info("Save role: " + role);
        URI uri = URI.create(fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/add-on-user")
    public ResponseEntity addToUser(@RequestBody RoleToUserFormDTO roleToUserForm) {
        log.info("Add role to user: " + roleToUserForm);
        userService.addRoleToUser(roleToUserForm.getUsername(), roleToUserForm.getRole());
        return ResponseEntity.ok().build();
    }

}

