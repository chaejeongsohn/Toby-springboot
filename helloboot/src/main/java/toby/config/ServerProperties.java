package toby.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * 서버를 설정하는데 필요한 property 값 모아두는 class
 */
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
