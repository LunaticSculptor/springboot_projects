//package com.example.ass2Practice.config;
//
//import io.r2dbc.spi.ConnectionFactories;
//import io.r2dbc.spi.ConnectionFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
//
//@Configuration
//@EnableR2dbcRepositories(
//        basePackages = "com.example.ass2Practice.repository.reactive"
//)
//public class R2dbcConfig {
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        return ConnectionFactories.get("r2dbc:h2:mem:///testdb");
//    }
//}
