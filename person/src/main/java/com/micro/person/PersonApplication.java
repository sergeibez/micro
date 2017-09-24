package com.micro.person;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Main application class
 * enables spring boot and discovery(Eureka) client
 *
 * @author Sergey Bezvershenko
 */
@EnableDiscoveryClient
public class PersonApplication {
	public static void main(String[] args) {
		SpringApplication.run(PersonApplication.class, args);
	}
}