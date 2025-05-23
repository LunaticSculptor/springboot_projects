package com.example.myPractice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAspectJAutoProxy
@EnableAsync
public class AppConfig {
    // Configuration class to enable AspectJ support
}