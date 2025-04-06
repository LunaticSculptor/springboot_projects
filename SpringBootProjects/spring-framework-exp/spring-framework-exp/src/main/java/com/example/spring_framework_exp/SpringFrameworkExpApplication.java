package com.example.spring_framework_exp;

import com.example.spring_framework_exp.enterprise.example.MyWebController;
import com.example.spring_framework_exp.game.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringFrameworkExpApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context =
				SpringApplication.run(SpringFrameworkExpApplication.class, args);

//		GamingConsole game = new SuperContra();
//		PacmanGame game = new PacmanGame();

//		MarioGame game = new MarioGame();
//		Runner runner = new Runner(game);

		GameRunner runner = context.getBean(GameRunner.class);
		runner.run();

		MyWebController controller = context.getBean(MyWebController.class);

		System.out.println(controller.returnValueFromBusinessService());
	}

}
