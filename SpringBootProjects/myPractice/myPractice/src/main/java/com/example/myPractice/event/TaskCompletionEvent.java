package com.example.myPractice.event;

import org.springframework.context.ApplicationEvent;


public class TaskCompletionEvent extends ApplicationEvent {

    private final int id;

    public TaskCompletionEvent(Object source, int id) {
        super(source);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
