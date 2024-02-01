package toby.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * MySpringBootConfig: Factory Method 가 담긴 class
 * @ Component
 *      ComponentScan 에 의해 해당 클래스가 Bean 으로 등록된다.
 * @ Configuration -> 이걸 사용한다!!
 *      Meta-Annotation 으로 @Component 가 이미 등록되어있다.
 */
//@Component
@Configuration
public class MySpringBootConfig {
    /**
     * Factory Method
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

}
