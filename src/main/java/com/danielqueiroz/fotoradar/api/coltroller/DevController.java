package com.danielqueiroz.fotoradar.api.coltroller;

import com.danielqueiroz.fotoradar.exception.AlreadyExistException;
import com.danielqueiroz.fotoradar.exception.ValidationException;
import com.danielqueiroz.fotoradar.model.Role;
import com.danielqueiroz.fotoradar.model.User;
import com.danielqueiroz.fotoradar.service.UserService;
import com.danielqueiroz.fotoradar.service.UserServiceImpl;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Slf4j
public class DevController {

	private final UserServiceImpl userService;

	public DevController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@GetMapping("/dev")
	public ResponseEntity<?> dev() throws AlreadyExistException, ValidationException {
		log.info("Salvando usuário dev");
		Role role = Role.builder()
				.name("USER")
				.build();

		Role roleOnDatabase = userService.saveRole(role);

		User user = User.builder()
				.username("teste")
				.name("Teste Tester")
				.email("teste@gmail.com")
				.cpf("123456789")
				.password("teste")
				.roles(Arrays.asList(roleOnDatabase))
				.build();
		Optional<User> userSaved = Optional.of(userService.saveUser(user));

		return ok().body(String.format("usuário salvo %s", userSaved));
	}

	@GetMapping("/status")
	public ResponseEntity<?> status() throws AlreadyExistException, ValidationException {
		return ok().body("Service is running");
	}

}
