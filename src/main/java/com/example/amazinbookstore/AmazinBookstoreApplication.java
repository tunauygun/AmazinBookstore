package com.example.amazinbookstore;

import com.example.amazinbookstore.Admin_users.Admin_User;
import com.example.amazinbookstore.Admin_users.Admin_UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class AmazinBookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazinBookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(Admin_UserRepository adminUserRepository) {
		return (args) -> {
			// Create some initial users
			Admin_User adminUser1 = new Admin_User(
					"Admin",
					true
			);

			Admin_User adminUser2 = new Admin_User(
					"Joe",
					false
			);

			Admin_User adminUser3 = new Admin_User(
					"Bob",
					false
			);

			// Save the books in the repository
			adminUserRepository.save(adminUser1);
			adminUserRepository.save(adminUser2);
			adminUserRepository.save(adminUser3);
		};
	}

}
