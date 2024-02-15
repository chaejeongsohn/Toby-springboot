package toby.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import toby.annotation.ConditionalMyOnClass;
import toby.annotation.MyAutoConfiguration;

/**
 * 유저 구성정보(ComponentScan), 자동 구성정보(AutoConfiguration)
 *    - 유저 구성정보에 등록된 빈이 우선적으로 등록된다.
 *
 * @ConditionalOnMissingBean
 *      ServletWebServerFactory 에 해당하는 Bean 이
 *      사용자가 직접 등록한게 없을때만 해당 메소드를 Bean 으로 등록한다.
 */

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
public class TomcatWebServerConfig {
    @Bean("tomcatServletWebServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(Environment environment){
        var webServerFactory = new TomcatServletWebServerFactory();
        webServerFactory.setPort(8990);
        webServerFactory.setContextPath(environment.getProperty("contextPath"));
        return webServerFactory;
    }
}

