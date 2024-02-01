package toby.helloboot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloServiceTest {

    @Test
    void simpleHelloService(){
        SimpleHelloService simpleHelloService = new SimpleHelloService();
        String response = simpleHelloService.sayHello("Test");

        assertThat(response).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(name -> name);
        String response = decorator.sayHello("Test");

        assertThat(response).isEqualTo("*Test*");
    }

}
