package com.tutum.example.back.TutumBack.app;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.tutum.example.back.TutumBack.app.utils.Constantes;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		String path = System.getProperty("path.properties");
		LOGGER.info("path.properties:{}", path);
		if (path == null || path == "") {
			LOGGER.info("No Args recibidos");
			SpringApplication.run(Application.class, args);
		} else {
			Constantes.PATH_ARCHIVO_PROPIEDADES = path;
			File archivo = new File(Constantes.PATH_ARCHIVO_PROPIEDADES);
			if (archivo.exists()) {
				SpringApplication.run(Application.class, args);
			} else {
				LOGGER.error("No se encontro el archivo de propiedades");
			}
		}
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}
