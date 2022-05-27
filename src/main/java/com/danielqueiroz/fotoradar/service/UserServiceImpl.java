package com.danielqueiroz.fotoradar.service;

import com.danielqueiroz.fotoradar.exception.AlreadyExistException;
import com.danielqueiroz.fotoradar.exception.ValidationException;
import com.danielqueiroz.fotoradar.repository.RoleRepo;
import com.danielqueiroz.fotoradar.repository.UserRepo;
import com.danielqueiroz.fotoradar.model.Role;
import com.danielqueiroz.fotoradar.model.User;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) throws AlreadyExistException, ValidationException {
        validateUser(user);

        log.info("Salvando usuário {}", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    private void validateUser(User user) throws AlreadyExistException, ValidationException {
        boolean exists = !Objects.isNull(getUser(user.getUsername()));
        if (exists) {
            throw  new AlreadyExistException("Usuário já cadastrado");
        }

        String password = user.getPassword();
        if (Strings.isNullOrEmpty(password)) {
            throw  new ValidationException("Senha não informada.");
        }
        if (password.length() < 5) {
            throw  new ValidationException("Senha precisa ter pelo menos 6 dígitos.");
        }
    }

    @Override
    public Role saveRole(Role role) {
        Role roleOnDatabase = roleRepo.findByName(role.getName());
        if (roleOnDatabase != null) {
            return roleOnDatabase;
        }
        log.info("Salvando regra {}", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adicionando regra {} no usuário {}", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Buscanod usuário {}", username );
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Buscando usuários");
        return userRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);
        if (user == null) {
            log.error("Usuário não encontrado.");
            throw new UsernameNotFoundException("Usuário não encontrado.");
        } else {
            log.info("Usuário {} encontrado.", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public void updateUser(User userToUpdate) throws ValidationException, AlreadyExistException {
        User user = userRepo.findByUsername(userToUpdate.getUsername());
        user.setUsername(userToUpdate.getUsername());
        user.setCpf(userToUpdate.getCpf());
        user.setName(userToUpdate.getName());
        user.setEmail(userToUpdate.getEmail());
        user.setPassword(userToUpdate.getPassword());
        saveUser(user);
    }
}
