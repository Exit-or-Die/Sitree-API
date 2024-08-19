package com.eod.sitree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
public class SitreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitreeApplication.class, args);
	}

}
