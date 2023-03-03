package com.danielqueiroz.fotoradar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories(basePackages = "com.danielqueiroz.fotoradar.repository")
public class FotoradarApplication {

	public static void main(String[] args) {
		SpringApplication.run(FotoradarApplication.class, args);
	}

}
