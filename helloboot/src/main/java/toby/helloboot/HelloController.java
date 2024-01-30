package toby.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public  class HelloController{
    private final HelloService helloService;
    /**
     * Bean 의 LifeCycleMethod 개념
     * 	-> Spring Container 가 초기화 되는 시점에 필요한 곳에 ApplicationContext 을 주입한다.
     * 	-> 메소드에서 생성자 없이 오브젝트 생성해서 리턴해도 잘 작동한다.
     */
    private final ApplicationContext applicationContext;

    public HelloController(HelloService helloService, ApplicationContext applicationContext) {
        this.helloService = helloService;
        this.applicationContext = applicationContext;

        System.out.println(applicationContext);
    }

    @GetMapping("/hello")
    public String hello(String name){
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}