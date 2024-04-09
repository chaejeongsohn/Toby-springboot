package toby.helloboot;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DataSource Test 관련 Meat-Annotation
 *
 * application.properties 를 등록하는 것은 spring-framework 의 기본 동작 방식이 아니다.
 * spring-boot 의 초기화 과정에서 추가해주는 것이다.
 *      그래서 자동으로 포함이 안된다.
 *      -> @PropertySource() 중에서 테스트용으로 사용하는 @TestPropertySource 을 이용한다.
 *      -> property 정보를 읽어오도록 한다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = HelloBootApplication.class)
@TestPropertySource("classpath:/application.properties")
@Transactional
public @interface HellobootTestAnnotation {
}
