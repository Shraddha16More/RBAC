package com.rbac;

import jakarta.transaction.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Transactional
public class RbacSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbacSystemApplication.class, args);
	}

}
