package toby.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @ SpringBootTest 을 붙이면
 *      -> 기존 main method 를 실행한 상태가 아니어도 test 가 가능하다.
 *      SpringBootTest.WebEnvironment.DEFINED_PORT
 *          property 에 있는 포트를 이용해서 웹서버를 띄우고 테스트 수행하라는 의미
 *  해당 어노테이션이 없으면,
 *      -> 기존 main method 가 있는 SpringBootApplication 을 실행한 상태에서, test 를 해야한다.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HelloApiTest {

    @Test
    void helloApi(){
        TestRestTemplate restTemplate = new TestRestTemplate();

        // given
        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:9090/app/hello?name={name}", String.class, "Spring");

        // when & then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        assertThat(response.getBody()).isEqualTo("*Hello Spring*");
    }

    @Test
    void failHelloApi() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        // given
        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:9090/app/hello?name=", String.class);

        // when & then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
