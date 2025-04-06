package com.example.webFlux.controller;

import com.example.webFlux.client.BookWebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final BookWebClient bookWebClient;
    @Autowired
    public PostController(BookWebClient bookWebClient) {
        this.bookWebClient = bookWebClient;
    }
    // GET: Fetch a post by ID using WebClient
    @GetMapping("/{id}")
    public Mono<String> getPostById(@PathVariable int id) {
        return bookWebClient.getPostById(id);
    }
    // POST: Create a post using WebClient
    @PostMapping
    public Mono<String> createPost(@RequestParam String title, @RequestParam String body, @RequestParam int userId) {
        return bookWebClient.createPost(title, body, userId);
    }
}