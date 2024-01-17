package toby.helloboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @ Configuration :
 * 		스프링 컨테이너가 @Bean 이 붙은 Factory Method 를 인지 할 수 있도록 한다.
 */
//
@Configuration
public class HelloBootApplication {
	/**
	 * Factory Method: 오브젝트를 생성하는 로직을 담고있는 메소드
	 * 		Bean 오브젝트(Factory Method)를 자바코드로 만들면 더 쉽고 간결해진다
	 */

	@Bean
	public HelloController helloController(HelloService helloService){
		return new HelloController(helloService);
	}

	@Bean
	public HelloService helloService(){
		return new SimpleHelloService();
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

				TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				serverFactory.setPort(8990);
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet",
							new DispatcherServlet(this)
					).addMapping("/*");
				});
				webServer.start();
			}
		};
		applicationContext.register(HelloBootApplication.class);
		applicationContext.refresh();

	}

}
