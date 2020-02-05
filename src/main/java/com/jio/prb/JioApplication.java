package com.jio.prb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JioApplication {

	private static final Logger LOGGER= LoggerFactory.getLogger(JioApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(JioApplication.class, args);
		LOGGER.info("Application Started");
	}

}
