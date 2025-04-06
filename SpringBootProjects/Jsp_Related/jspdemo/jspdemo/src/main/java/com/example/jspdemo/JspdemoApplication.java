package com.example.jspdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class JspdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JspdemoApplication.class, args);
	}

}

//@Controller
//class HelloController {
//	@GetMapping("/hi")
//	public String hello() {
//		return "hello";
//	}
//}
