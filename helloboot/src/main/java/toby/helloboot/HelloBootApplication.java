package toby.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
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
		 * GenericWebApplicationContext 은 자바코드로 만든 Configuration 정보를 읽을 수 없다.
		 * 		-> AnnotationConfigWebApplicationContext 으로 변경한다
		 */
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
			@Override
			protected void onRefresh() {
				super.onRefresh();

				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);

				/**
				 * DispatcherServlet 에 ApplicationContext 를 주입한다.
				 * 하지만 이걸 직접하지 않더라도, Spring Container 가 알아서 주입해준다!!!
				 * 	-> Bean 의 LifeCycleMethod 개념
				 * 	-> Spring Container 가 초기화 되는 시점에일어난다.
				 */
				//dispatcherServlet.setApplicationContext(this);

				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet", dispatcherServlet)
							.addMapping("/*");
				});
				webServer.start();
			}
		};
		applicationContext.register(HelloBootApplication.class);
		applicationContext.refresh();

	}

}
