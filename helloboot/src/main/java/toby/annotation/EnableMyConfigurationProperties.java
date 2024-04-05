package toby.annotation;

import org.springframework.context.annotation.Import;
import toby.config.MyConfigurationPropertiesImportSelector;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Enable 이라는 말로 시작하는 annotation 대부분 활용 용도:
 *      Import annotation 을 다시 넣어서
 *      어떤 기능을 가진 Configuration Class 나 Import selector 등을 가져온다.
 *      (여기선 MyConfigurationPropertiesImportSelector 가져옴)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyConfigurationPropertiesImportSelector.class)
public @interface EnableMyConfigurationProperties {
    Class<?> value();
}
