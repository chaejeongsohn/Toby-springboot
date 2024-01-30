package toby.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @ Configuration :
 * 		스프링 컨테이너가 @Bean 이 붙은 Factory Method 를 인지 할 수 있도록 한다.
 * @ ComponentScan :
 * 		컴포넌트 스캐너는 annotation 이 붙은 모든 클래스를 찾아서 Bean 으로 등록해준다.
 * 		Bean 으로 등록할 클래스에 @ Component 만 추가해주면 되니 간단하다.
 * 		단점: 하지만 너무 많아지면 한눈에 보기 어렵다.
 */
@Configuration
@ComponentScan
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
		/**
		 * 이때까지 만든 MySpringApplication 이 SpringApplication 을 구현한 것과 같다.
		 */
//		MySpringApplication.run(HelloBootApplication.class, args);
		SpringApplication.run(HelloBootApplication.class, args);
	}
}
