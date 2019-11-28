package com.pi.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( basePackages = "com.pi" )
public class PiManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiManagerApplication.class, args);
	}

}
