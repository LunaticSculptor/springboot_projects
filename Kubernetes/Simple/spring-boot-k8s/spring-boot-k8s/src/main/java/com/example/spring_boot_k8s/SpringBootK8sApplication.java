package com.example.spring_boot_k8s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringBootK8sApplication {

	@GetMapping("/message")
	public String welcome(){
		return "Congratulation you successfully deployed your application to kubernetes !!";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootK8sApplication.class, args);
	}

}
