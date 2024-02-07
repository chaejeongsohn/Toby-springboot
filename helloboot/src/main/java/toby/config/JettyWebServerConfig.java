package toby.config;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import toby.annotation.MyAutoConfiguration;

/**
 * @ Conditional
 *      : Configuration 중에서 어떤 경우에 적용이 되고 적용되지 않는가를 선택하게 만드는 방법을 추가해야한다.
 *      Element Type : 인터페이스 Condition 을 구현한 class
 *
 *      ConditionContext
 *          현재 Spring Container 와 Application 이 돌아가는 환경 정보에 관한 Object 리턴해준다.
 *      AnnotatedTypeMetadata
 *          Conditional 이 붙은 해당 Meta-Annotation 으로 사용하는
 *          다른 Annotation 정보들을 이용할 수 있도록
 *          Annotation 관련 Meta 데이터 리턴해준다.
 */
@MyAutoConfiguration
@Conditional(JettyWebServerConfig.JettyCondition.class)
public class JettyWebServerConfig {
    @Bean("jettyServletWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory(){
        var webServerFactory = new JettyServletWebServerFactory();
        webServerFactory.setPort(8990);
        return webServerFactory;
    }

    static class JettyCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            return true;
        }
    }
}
