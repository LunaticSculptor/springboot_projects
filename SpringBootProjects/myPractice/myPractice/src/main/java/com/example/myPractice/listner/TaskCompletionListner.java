package com.example.myPractice.listner;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Async
@Component
public class TaskCompletionListner {

    @EventListener
    public void publishEvent(int id) {
        System.out.println("Event with id: " + id + " is completed");
    }
}
