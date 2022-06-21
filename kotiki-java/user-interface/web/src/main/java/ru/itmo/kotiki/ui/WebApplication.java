package ru.itmo.kotiki.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ru.itmo.kotiki.ui.repository"})
@EntityScan(basePackages = {"ru.itmo.kotiki.ui"})
@ComponentScan({
		"ru.itmo.kotiki.ui.repository",
		"ru.itmo.kotiki.ui.service",
		"ru.itmo.kotiki.ui.controller",
		"ru.itmo.kotiki.ui.security",
		"ru.itmo.kotiki.ui.config"})
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
