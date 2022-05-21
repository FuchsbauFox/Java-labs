package ru.itmo.kotiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ru.itmo.kotiki.repository"})
@EntityScan(basePackages = {"ru.itmo.kotiki"})
@ComponentScan({
		"ru.itmo.kotiki.repository",
		"ru.itmo.kotiki.service",
		"ru.itmo.kotiki.controller",
		"ru.itmo.kotiki.security",
		"ru.itmo.kotiki.config"})
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
