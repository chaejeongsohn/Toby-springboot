package toby.annotation;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Composed-Annotation
 * @ Retention
 *      RetentionPolicy.CLASS: 기본값
 *      : 컴파일된 class 파일까지는 살아있지만,
 *        해당 클래스를 runtime 에 메모리로 로딩할 때는, 그 정보 사라진다.
 *      RetentionPolicy.RUNTIME
 *  @ Target
 *      ElementType.TYPE
 *      : class, interface, enum 이 세 종류에 부여가능하다.
 *  @ ComponentScan
 *      : 해당 Annotation 기준 package 로 scan 한다.
 *  @ EnableMyAutoConfig
 *      :따로 작성한 Auto-Configuration 을 추가해서 config 정보를 받아온다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ComponentScan
@Configuration
@EnableMyAutoConfig
public @interface MySpringBootApplication {
}
