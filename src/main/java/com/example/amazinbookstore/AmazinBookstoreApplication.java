package com.example.amazinbookstore;

import com.example.amazinbookstore.books.Book;
import com.example.amazinbookstore.books.BookRepository;
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
	public CommandLineRunner demo(BookRepository bookRepository) {
		return (args) -> {
			// Create some initial books
			Book book1 = new Book(
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

			Book book2 = new Book(
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

			Book book3 = new Book(
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
			bookRepository.save(book1);
			bookRepository.save(book2);
			bookRepository.save(book3);
		};
	}

}
