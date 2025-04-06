package com.example.spring_framework_exp.enterprise.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataService{
    @Autowired
    public List<Integer> getData(){
        return Arrays.asList(10, 20, 30, 40, 50);
    }
}
