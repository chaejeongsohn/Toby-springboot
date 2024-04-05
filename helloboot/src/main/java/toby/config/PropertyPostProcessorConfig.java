package toby.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import toby.annotation.MyAutoConfiguration;
import toby.annotation.MyConfigurationProperties;

import java.util.Map;

import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * <Bean post processer (빈 후처리기)>
 * 사용 목적:
 * @MyConfigurationProperties
 *      property 전용 annotation 을 만들고,
 *      해당 config 에서 위 annotation 이 붙은 property 에 property bean 을 만들어 준다.
 *      ( property 에 일일이 bean 생성 코드를 넣을 필요가 없다.)
 *
 * postProcessAfterInitialization
 *      Bean 오브젝트의 초기화가 끝난 다음에 이 프로세스를 요청함
 */
@MyAutoConfiguration
public class PropertyPostProcessorConfig {

    @Bean BeanPostProcessor propertyPostProcessor(Environment environment){
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                /**
                 * 1. @MyConfigurationProperties 라는 이름의 annotation 을 찾는다.
                 */
                MyConfigurationProperties annotation = findAnnotation(bean.getClass(), MyConfigurationProperties.class);

                /**
                 *  2 -1. 못 찾으면 아무런 후처리 가공을 하지 않고 넘긴다
                 */
                if (annotation == null) return bean;

                /**
                 * 모든 Attributes 의 정보를 Map 형태로 받는다.
                 * 해당 이름을 아래 bind 의 name 에 넣어서 사용한다.
                 */
                Map<String, Object> attributes = AnnotationUtils.getAnnotationAttributes(annotation);
                String prefix = (String) attributes.get("prefix");

                /**
                 * 2-2. property 주입이 필요한 오브젝트는 바인딩을 시도했는데 없다면 이 클래스에 오브젝트를 만들어서 리턴한다.
                 *
                 * Property Binding 을 할 때 prefix 를 붙여서
                 * 해당 클래스의 property 이름과 일치하는지 확인한다.
                 */
                return Binder.get(environment).bindOrCreate(prefix, bean.getClass());
            }
        };
    }
}
