package toby.config;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import toby.annotation.ConditionalMyOnClass;
import toby.annotation.MyAutoConfiguration;

/**
 * @Conditional
 *      Configuration 중에서 어떤 경우에 적용이 되고 적용되지 않는가를 선택하게 만드는 방법을 추가해야한다.
 *      - Element Type : 인터페이스 Condition 을 구현한 class
 *                      JettyWebServerConfig.JettyCondition.class
 *      - ConditionContext
 *          현재 Spring Container 와 Application 이 돌아가는 환경 정보에 관한 Object 리턴해준다.
 *      - AnnotatedTypeMetadata
 *          @Conditional 이 붙은 Annotation 을 Meta-Annotation 으로 사용하는 Annotation 의 Meta 데이터를 가져온다.
 */
@MyAutoConfiguration
@ConditionalMyOnClass("org.eclipse.jetty.server.Server")
//@Conditional(JettyWebServerConfig.JettyCondition.class)
public class JettyWebServerConfig {
    @Bean("jettyServletWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory(){
        var webServerFactory = new JettyServletWebServerFactory();
        webServerFactory.setPort(8990);
        return webServerFactory;
    }

//    static class JettyCondition implements Condition {
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//            return ClassUtils.isPresent("org.eclipse.jetty.server.Server",
//                    context.getClassLoader());
//        }
//    }
}
