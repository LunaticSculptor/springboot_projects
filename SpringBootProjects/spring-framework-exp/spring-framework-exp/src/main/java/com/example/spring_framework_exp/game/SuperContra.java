package com.example.spring_framework_exp.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SuperContra implements GamingConsole{
    public void up(){
        System.out.println("Super up");
    }
    public void down(){
        System.out.println("Super down");
    }
    public void left(){
        System.out.println("Super left");
    }
    public void right(){
        System.out.println("Super right");
    }
}
