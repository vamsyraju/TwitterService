package com.social.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan("com.social")
//@ImportResource("classpath:spring/application-config.xml")
public class Application {
	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}
}
