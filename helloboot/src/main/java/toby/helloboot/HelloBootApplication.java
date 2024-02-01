package toby.helloboot;

import org.springframework.boot.SpringApplication;
import toby.annotation.MySpringBootApplication;

//@SpringBootApplication
@MySpringBootApplication
public class HelloBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloBootApplication.class, args);
	}
}
