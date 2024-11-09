package com.example.amazinbookstore;

import com.example.amazinbookstore.Admin_books.Admin_Book;
import com.example.amazinbookstore.Admin_books.Admin_BookRepository;
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
	public CommandLineRunner demo(Admin_BookRepository adminBookRepository) {
		return (args) -> {
			// Create some initial books
			Admin_Book adminBook1 = new Admin_Book(
					Arrays.asList("Author One", "Author Two"),
					"Fantasy",
					"978-3-16-148410-0",
					350,
					29.99,
					"Publisher A",
					10,
					"The Adventures",
					"https://example.com/book1",
					2024
			);

			Admin_Book adminBook2 = new Admin_Book(
					Arrays.asList("Author Three"),
					"Science Fiction",
					"978-1-40-289462-6",
					200,
					19.99,
					"Publisher B",
					15,
					"Journey to the West",
					"https://example.com/book2",
					2023
			);

			Admin_Book adminBook3 = new Admin_Book(
					Arrays.asList("Author Four", "Author Five"),
					"Romance",
					"978-0-13-468599-1",
					450,
					24.99,
					"Publisher C",
					20,
					"Love in the Time of Algorithms",
					"https://example.com/book3",
					2022
			);

			// Save the books in the repository
			adminBookRepository.save(adminBook1);
			adminBookRepository.save(adminBook2);
			adminBookRepository.save(adminBook3);
		};
	}

}
