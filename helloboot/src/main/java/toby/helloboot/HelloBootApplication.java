package toby.helloboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
public class HelloBootApplication {

	public static void main(String[] args) {
		// DispatcherServlet 은 WebApplicationContext 타입을 전송해줘야한다.
		// 스프링 컨테이너의 작업은 refresh() 라는 메소드에서 다 일어난다
		GenericWebApplicationContext applicationContext = new GenericWebApplicationContext(){
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
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh();

	}

}
