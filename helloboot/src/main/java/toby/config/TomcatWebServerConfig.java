package toby.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import toby.annotation.ConditionalMyOnClass;
import toby.annotation.EnableMyConfigurationProperties;
import toby.annotation.MyAutoConfiguration;

import java.util.Objects;

/**
 * 유저 구성정보(ComponentScan), 자동 구성정보(AutoConfiguration)
 *    - 유저 구성정보에 등록된 빈이 우선적으로 등록된다.
 *
 * @ConditionalOnMissingBean
 *      ServletWebServerFactory 에 해당하는 Bean 이
 *      사용자가 직접 등록한게 없을때만 해당 메소드를 Bean 으로 등록한다.
 *--------------------------------------------------
 * <properties 값을 가져오기>
 * @Import(ServerProperties.class)
 *      Bean 바로 주입
 *      PropertyPostProcessorConfig 조건을 만족하는 경우에만
 *      이 property 클래스가 bean 으로 로딩이 된다.
 *      (= 필요한 경우에만 만들어진다)
 *
 * @EnableMyConfigurationProperties
 *      Import annotation 로 직접 server property 를 가져오는 대신
 *      element 값으로 읽어오도록 한다.
 */
@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
//@Import(ServerProperties.class)
@EnableMyConfigurationProperties(ServerProperties.class)
public class TomcatWebServerConfig {

    /**
     * 1-1. Bean 클래스에 Field 를 만들어 놓고
     */
//    @Value("${contextPath:/myapp}")
//    String contextPath;
//
//    @Value("${port:8990}")
//    int port;

    /**
     * 2-1. 서버를 설정하는데 필요한 property(ServerProperties) 값을 pram 으로 들고온다
     */
    @Bean("tomcatServletWebServerFactory")
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(ServerProperties properties){
        var webServerFactory = new TomcatServletWebServerFactory();

        /**
         * 1-2. 이 Field 에 프로퍼티에서 읽어온 값을 주입해준다.
         */
//        webServerFactory.setPort(port);
//        webServerFactory.setContextPath(contextPath);

        /**
         * 2-2. 서버 팩토리를 만들 때 설정 해준다.
         */
        webServerFactory.setPort(properties.getPort());
        webServerFactory.setContextPath(properties.getContextPath());

        return webServerFactory;
    }
}

