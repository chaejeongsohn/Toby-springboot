package tobyspring.helloboot;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

@SpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
		// spring container 생성
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		// Bean 오브젝트 생성 (class 정보 넘겨주기)
		applicationContext.registerBean(HelloController.class);
		applicationContext.registerBean(SimpleHelloSevice.class);
		// 구성정보로 container 초기화
		applicationContext.refresh();


		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {
				@Override
				public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
					// 인증, 보안, 다국어, 공통 기능
					if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){
						String name = req.getParameter("name");

						HelloController helloController = applicationContext.getBean(HelloController.class);
						// 바인딩
						String ret = helloController.hello(name);

						res.setContentType(MediaType.TEXT_PLAIN_VALUE);
						res.getWriter().println(ret);
					}
					else {
						res.setStatus(HttpStatus.NOT_FOUND.value());
					}

				}
			}).addMapping("/*");
		});
		webServer.start();

	}

}
