package com.example.jspdemo.service;

import com.example.jspdemo.controller.Todo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();
    static {
        todos.add(new Todo(1, "ramesh", "science",
                LocalDate.now().plusYears(1), false));
        todos.add(new Todo(2, "suresh", "arts",
                LocalDate.now().plusYears(2), false));
        todos.add(new Todo(3, "ganesh", "commerce",
                LocalDate.now().plusYears(3), false));
    }
    public List<Todo> findByUsername(String username) {
        return todos;
    }
}
