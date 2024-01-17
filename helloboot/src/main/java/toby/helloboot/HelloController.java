package toby.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@RequestMapping
public  class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    /**
     * DispatcherServlet 은
     * @RestController 가 붙는 클래스는
     * (org.springframework.web.bind.annotation.RestController)
     * @ ResponseBody 가 자동으로 붙어있다고 가정하기에,
     * @RestController 를 쓸 경우에는 @ResponseBody 사용할 필요가 없다.
     */
    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name){
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}