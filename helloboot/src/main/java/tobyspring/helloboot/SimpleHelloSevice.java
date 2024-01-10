package tobyspring.helloboot;

public class SimpleHelloSevice implements HelloSevice {
    @Override
    public String sayhello(String name) {
        return "Hello "+ name;
    }
}
