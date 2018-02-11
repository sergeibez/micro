package com.micro.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class
 * enables spring boot and discovery(Eureka) client
 *
 * @author Sergey Bezvershenko
 */
//@EnableDiscoveryClient
@SpringBootApplication
public class AuthApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}
