package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.demo.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableCaching
@Slf4j
public class InventaryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventaryManagementSystemApplication.class, args);
		log.info("In Spring boot class");
	}
}
