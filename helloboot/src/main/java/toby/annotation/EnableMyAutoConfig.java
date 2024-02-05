package toby.annotation;

import org.springframework.context.annotation.Import;
import toby.config.MyAutoConfigImportSelector;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Auto-Configuration
 * 미리 준비된 Configuration 들을 SpringBoot 가 미리 판단하고 자동으로 사용하게 해준다.
 * @ Import
 *      : @ComponentScan 이 붙은 어노테이션에 class 정보를 넘겨서 Configuration 정보에 추가한다
 *      package 가 달라도 (스캔 대상이 아니더라도) 스캔이 된다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyAutoConfigImportSelector.class)
public @interface EnableMyAutoConfig {
}
