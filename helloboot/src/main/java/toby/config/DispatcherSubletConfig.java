package toby.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class DispatcherSubletConfig {
    @Bean
    public DispatcherServlet dispatcherServlet(){
        return new DispatcherServlet();
    }

}
