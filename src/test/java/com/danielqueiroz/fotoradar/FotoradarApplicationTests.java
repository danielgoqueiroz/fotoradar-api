package com.danielqueiroz.fotoradar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@TestPropertySource(locations = "classpath:application-test.properties")
class FotoradarApplicationTests {

	@Test
	void contextLoads() {
	}

}
