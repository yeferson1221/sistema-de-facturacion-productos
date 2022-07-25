package com.yefer.venta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * contiene la clase main ejecuta todo el proyecto
 * @author [Yeferson Valencia, alejandro.yandd@gmail.com.
 *
 * @since [1.0.0]
 *
 */
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
