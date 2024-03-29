package com.project.dmstodolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class DmsTodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmsTodolistApplication.class, args);
	}

}
