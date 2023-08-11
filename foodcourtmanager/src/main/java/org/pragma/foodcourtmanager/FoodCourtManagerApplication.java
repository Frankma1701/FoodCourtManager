package org.pragma.foodcourtmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FoodCourtManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodCourtManagerApplication.class, args);
	}

}
