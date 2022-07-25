package com.yefer.relaciones;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RelacionesApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(RelacionesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RelacionesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		LOG.info("Creado: Yeferson Valencia --- Correo Alejandro.yandd@gmail.com");

	}
}
