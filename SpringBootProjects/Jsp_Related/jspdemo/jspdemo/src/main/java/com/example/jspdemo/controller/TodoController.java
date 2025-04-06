package com.example.jspdemo.controller;

import com.example.jspdemo.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("/list-todos")
    public String getAllTodos(ModelMap model) {
        List<Todo> todos = todoService.findByUsername("ram");
        model.addAttribute("todos", todos);
        return "listTodos";
    }
}
