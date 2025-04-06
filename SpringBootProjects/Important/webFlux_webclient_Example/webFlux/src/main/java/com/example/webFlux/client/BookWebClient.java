package com.example.webFlux.client;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
@Component
public class BookWebClient {
    private final WebClient webClient;
    public BookWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://jsonplaceholder.typicode.com").build();
    }
//    // Fetch a post by ID (GET Request)
//    public Mono<String> getPostById(int postId) {
//        return webClient.get()
//                .uri("/posts/{id}", postId)  // URI with path variable
//                .retrieve()
//                .bodyToMono(String.class);  // Expect a single result as a String
//    }
    public Mono<String> getPostById(int postId) {
        return webClient.get()
                .uri("/posts/{id}", postId)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new WebClientResponseException("Client error", clientResponse.statusCode().value(), "", null, null, null))))
                .onStatus(status -> status.is5xxServerError(), clientResponse ->
                        clientResponse.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new WebClientResponseException("Server error", clientResponse.statusCode().value(), "", null, null, null))))
                .bodyToMono(String.class);
    }
    // Create a new post (POST Request)
    public Mono<String> createPost(String title, String body, int userId) {
        return webClient.post()
                .uri("/posts")
                .bodyValue(new Post(title, body, userId))  // Pass the request body
                .retrieve()
                .bodyToMono(String.class);  // Expect the response as a String
    }

    // A sample model for the POST request body
    static class Post {
        private String title;
        private String body;
        private int userId;
        // Constructor, Getters, and Setters...
        public Post(String title, String body, int userId) {
            this.title = title;
            this.body = body;
            this.userId = userId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
