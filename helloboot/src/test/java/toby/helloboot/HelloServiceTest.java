package toby.helloboot;

import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Meta-Annotation
 * Annotation 에는 @Retention 과 @Target 정보 필수
 * @ Target(ElementType.METHOD)
 *      : 해당 어노테이션은 method 에 붙을 수 있다.
 * @ Target(ElementType.ANNOTATION_TYPE)
 *      : 해당 어노테이션은 Annotation 에 붙을 수 있다.
 *      : ANNOTATION_TYPE 이라는 위치에 사용할 수 있어야지만 Meta-Annotation 에 사용할 수 있다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest{}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Test
@interface UnitTest{}

public class HelloServiceTest {

    @FastUnitTest
    void simpleHelloService(){
        SimpleHelloService simpleHelloService = new SimpleHelloService(helloRepositoryStub);
        String response = simpleHelloService.sayHello("Test");

        assertThat(response).isEqualTo("Hello Test");
    }

    private static final HelloRepository helloRepositoryStub = new HelloRepository() {
            @Override
            public Hello findHello(String name) {
                return null;
            }

            @Override
            public void increaseCount(String name) {

            }
    };


    @Test
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(name -> name);
        String response = decorator.sayHello("Test");

        assertThat(response).isEqualTo("*Test*");
    }

}
