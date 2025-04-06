package com.example.spring_framework_exp.game;

import org.springframework.stereotype.Component;

@Component
public class MarioGame implements GamingConsole{
    public void up(){
        System.out.println("Mario up");
    }
    public void down(){
        System.out.println("Mario down");
    }
    public void left(){
        System.out.println("Mario left");
    }
    public void right(){
        System.out.println("Mario right");
    }
}
