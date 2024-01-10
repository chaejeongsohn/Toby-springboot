package tobyspring.helloboot;

import java.util.Objects;

public  class HelloController {
    // Spring Container가 해당 클래스의 오브젝트를 만들때, 생성자 파라미터로 주입할 수 있도록 함
    private final HelloSevice helloSevice;

    public HelloController(HelloSevice helloSevice) {
        this.helloSevice = helloSevice;
    }

    public String hello(String name){
        // 특정 클래서를 직접 인스턴스 생성 X,
//        SimpleHelloSevice helloSevice = new SimpleHelloSevice();

        // Objects.requireNonNull() : Null 인 경우에 예외처리해주는 메소드
        return  helloSevice.sayHello(Objects.requireNonNull(name));
    }
}