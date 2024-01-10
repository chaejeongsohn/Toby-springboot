package tobyspring.helloboot;

public class SimpleHelloSevice implements HelloSevice {
    @Override
    public String sayHello(String name) {
        return "Hello "+ name;
    }
}
