package toby.config;

import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import toby.annotation.MyAutoConfiguration;

import java.util.Objects;


@MyAutoConfiguration
public class ServerPropertiesConfig {

    /**
     * 1-1. 서버를 설정하는데 필요한 property 를 담아둔 클래스(ServerProperties)의 property 값을 가져온다
     *  -> 1-3. Bean 으로 등록 후 사용
     */
    @Bean
    public ServerProperties serverProperties(Environment environment) {
        var properties = new ServerProperties();

        /**
         * 1-2. property 값을 가져온다
         */
//        properties.setContextPath(environment.getProperty("contextPath"));
//        properties.setPort(Integer.parseInt(Objects.requireNonNull(environment.getProperty("port"))));
//
//        return properties;

        /**
         * 2. Binder 이용 해서 property file 에 있는 값 읽어오기
         */
        return Binder.get(environment).bind("", ServerProperties.class).get();
    }
}
