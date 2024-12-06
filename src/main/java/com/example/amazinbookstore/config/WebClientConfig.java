package com.example.amazinbookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient bookCoverWebClient(WebClient.Builder builder) {
        return builder.baseUrl("https://bookcover.longitood.com/").build();
    }
}
