package com.example.crud.event;

import org.springframework.context.ApplicationEvent;

public class NewUserEvent extends ApplicationEvent {

    private String userName;

    public NewUserEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
