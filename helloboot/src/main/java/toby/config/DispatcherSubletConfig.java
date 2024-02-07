package toby.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import toby.annotation.MyAutoConfiguration;

@MyAutoConfiguration
public class DispatcherSubletConfig {
    @Bean
    public DispatcherServlet dispatcherServlet(){
        return new DispatcherServlet();
    }

}
