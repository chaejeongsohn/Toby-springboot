package toby.helloboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation 생성
 *      Meta Annotation: Annotation 위에 붙이는 Annotation
 * Component : Meta Annotation 으로 넣는다.
 * Retention : 이 Annotation 이 어디까지 살아있나, 언제까지 유지되나 설정한다.
 * Target : Annotation 을 적용할 대상을 지정해준다.
 */
@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyComponent {
}
