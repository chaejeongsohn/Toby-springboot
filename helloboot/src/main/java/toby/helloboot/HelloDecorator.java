package toby.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @ Primary
 *  Bean 이 두 개 이상일때, 우선적으로 해당 빈을 사용함.
 */
@Service
@Primary
public class HelloDecorator implements HelloService{
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }
}
