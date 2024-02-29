package toby.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import toby.annotation.MyAutoConfiguration;

/**
 * application.properties 값을 읽어오기 위해 해당 클래스 작성함.
 *
 * @Value 에 있는 값을 치환하기 위해서는
 * PropertySourcesPlaceholderConfigurer 빈이 먼저 등록되어야 한다.
 */
@MyAutoConfiguration
public class PropertyPlaceholderConfig {
    @Bean
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
