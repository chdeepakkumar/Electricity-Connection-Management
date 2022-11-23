package com.project.electricityConns;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:logmessages.properties")
public class ElectricityConnectionManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectricityConnectionManagementApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapperBean() {
		return new ModelMapper();
	}
}