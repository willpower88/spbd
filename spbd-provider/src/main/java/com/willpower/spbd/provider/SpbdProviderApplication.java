package com.willpower.spbd.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:provider.xml")
public class SpbdProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpbdProviderApplication.class, args);
	}

}
