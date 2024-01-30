package toby.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
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
