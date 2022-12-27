package com.candidatura.espublico;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EspublicoApplication {

	private static final Logger log = LoggerFactory.getLogger(EspublicoApplication.class);

	public static void main(String[] args) {
		log.debug("----------------------Inicializando la aplicación----------------------------");
		log.debug("-Dbbddusername="+System.getenv("bbddusername"));
		log.debug("-Dbbddpassword="+System.getenv("bbddpassword"));

		SpringApplication.run(EspublicoApplication.class, args);

	}

}
