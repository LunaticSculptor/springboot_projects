package com.example.spring_framework_exp.enterprise.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.List;

@Component
public class MyWebController {
    @Autowired
    private BusinessService businessService;
    public long returnValueFromBusinessService () {
        return businessService.calculateSum();
    }
}

