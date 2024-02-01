package toby.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class HelloBootApplication {
	/**
	 * @ Bean 으로 등록해서 Spring Container 가 빈을 관리하도록 하는게 더 좋다.
	 * 	-> this.getBean() 을 이용해서 Spring Container 에서 가져온다.
	 */
	@Bean
	public ServletWebServerFactory servletWebServerFactory(){
		var tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
		tomcatServletWebServerFactory.setPort(8990);
		return tomcatServletWebServerFactory;
	}

	@Bean
	public DispatcherServlet dispatcherServlet(){
		return new DispatcherServlet();
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloBootApplication.class, args);
	}
}
