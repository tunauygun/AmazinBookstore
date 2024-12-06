package com.example.amazinbookstore.controllers;

import com.example.amazinbookstore.config.WebClientConfig;
import com.example.amazinbookstore.dto.BookCoverUrl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bookCover/")
public class BookCoverController {

    private final WebClient bookCoverWebClient;

    public BookCoverController(WebClient bookCoverWebClient) {
        this.bookCoverWebClient = bookCoverWebClient;
    }

    @GetMapping("/{isbn}")
    public Mono<BookCoverUrl> findById(@PathVariable("isbn") String isbn) {
        return bookCoverWebClient.get()
                .uri("/bookcover/{isbn}", isbn)
                .retrieve()
                .bodyToMono(BookCoverUrl.class);
    }

}
