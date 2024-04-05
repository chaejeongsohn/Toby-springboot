package toby.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import toby.annotation.MyConfigurationProperties;

/**
 *  <서버를 설정하는데 필요한 property 값 모아두는 class>
 *
 *  * Annotation 을 붙이고 prefix 를 달고 있는 property class 를 만든다.
 *  * 해당 property class 들을 @MyAutoConfiguration 을 달고 있는 class 에서 (여기선 PropertyPostProcessorConfig)
 *  * 자동 property 의 default 값을 override 해서 세밀한 설정을 한다.
 *
 * 프로퍼티 값을 바인딩해야한다.
 *      방법: Bean post processer (빈 후처리기) 이용
 *      ( 여기선 PropertyPostProcessorConfig 이용)
 *      해당 config 는 @MyAutoConfiguration 자동 구성 설정이다.
 *      해당 coifig 에서 어떤 annotation 을 살펴보고 property 값을 binding 할지 적혀있다.
 *          ->  PropertyPostProcessorConfig 내부에 @MyConfigurationProperties 을 확인하는 로직이 포함되어 있다.
 *
 * (prefix = "server")
 *      해당 class 에 나오는 property 에 대한 namespace 역할을 해준다.
 */
//@Component
@MyConfigurationProperties(prefix = "server")
public class ServerProperties {

    private String contextPath;

    private int port;

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
