package com.example.crud.listner;

import com.example.crud.event.NewUserEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class NewUserListner {

    @Async
    @EventListener
    public void handleNewUserEvent(NewUserEvent event) {
        System.out.println("Hello, New User: " + event.getUserName());
    }
}
