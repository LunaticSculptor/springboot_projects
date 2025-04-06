package com.example.webFlux.client;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Component;
@Component
public class MyWebClient {
    private final WebClient webClient;

    // Using WebClient.builder for more flexibility
    public MyWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }

    // Simplified version using WebClient.create()
    public MyWebClient() {
        this.webClient = WebClient.create();
    }
}
