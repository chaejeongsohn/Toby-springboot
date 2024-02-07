package toby.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


public class ConditionalTest {

    /**
     * conditionalTrueTest & conditionalFalseTest
     *      @Conditional 의 ConditionContext Test
     *      현재 Spring Container 와 Application 이 돌아가는 환경 정보 return
     */
    @Test
    void conditionalTrueTest() {
        /**
         * ApplicationContextRunner()
         *      - Test 전용 ApplicationContext
         *      - 어떤 Bean 이 등록됐는가 등록되지 않았는가 확인한다.
         */
        var contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(ConfigTrueCondition.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(MyBean.class);
                    assertThat(context).hasSingleBean(ConfigTrueCondition.class);
                });

    }

    @Test
    void conditionalFalseTest() {
        var contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(ConfigFalseCondition.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(MyBean.class);
                    assertThat(context).doesNotHaveBean(ConfigFalseCondition.class);
                });
    }

    @Test
    void conditionalBooleanTrueTest() {
        var contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(ConfigBooleanTrueCondition.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(MyBean.class);
                    assertThat(context).hasSingleBean(ConfigBooleanTrueCondition.class);
                });

    }

    @Test
    void conditionalBooleanFalseTest() {
        var contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(ConfigBooleanFalseCondition.class)
                .run(context -> {
                    assertThat(context).doesNotHaveBean(MyBean.class);
                    assertThat(context).doesNotHaveBean(ConfigBooleanFalseCondition.class);
                });

    }


    /**
     * 등록이 되는 Bean
     * @Conditional
     *      - Element Type : 인터페이스 Condition 을 구현한 class
     *                      TrueCondition.class ( return true )
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(TrueCondition.class)
    @interface TrueConditional {}

    @Configuration
    @TrueConditional
    //    @Conditional(TrueCondition.class)
    static class ConfigTrueCondition {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    /**
     * 등록이 안 되는 Bean
     * @Conditional
     *      - Element Type : 인터페이스 Condition 을 구현한 class
     *                      FalseCondition.class ( return false )
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(FalseCondition.class)
    @interface FalseConditional {}

    @Configuration
    @FalseConditional
    //    @Conditional(FalseCondition.class)
    static class ConfigFalseCondition {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    /**
     * Meta 데이터의 정보에 따라 등록 여부가 다른 Bean
     * @Conditional
     *      - Element Type : 인터페이스 Condition 을 구현한 class
     *                      BooleanCondition.class ( return boolean )
     *      AnnotatedTypeMetadata
     *          @Conditional 이 붙은 Annotation 을 Meta-Annotation 으로 사용하는 Annotation 의 Meta 데이터를 가져온다.
     *          - @Configuration
     *          - @BooleanConditional(true)
     *          두 정보를 가져온다.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional{
        boolean value();
    }

    @Configuration
    @BooleanConditional(true)
    static class ConfigBooleanTrueCondition {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class ConfigBooleanFalseCondition {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    static class MyBean{}


    /**
     *  인터페이스 Condition 을 구현한 Condition class
     */
    static class TrueCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }

    static class FalseCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return false;
        }
    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            /**
             * Conditional(Annotation) 의 getName()
             *      : 해당 Conditional 이 사용되는 환경에서 이 Annotation 에 붙어있는 속성값 읽기
             *        @BooleanConditional 이 사용되는 환경의 속성값 읽어온다.
             *        그리고 속성값의 default key 는 "value" 이다.
             */
            Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            return (boolean) Objects.requireNonNull(annotationAttributes).get("value");
        }
    }
}
