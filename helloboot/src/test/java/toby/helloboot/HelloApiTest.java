package toby.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class HelloApiTest {

    @Test
    void helloApi(){
        TestRestTemplate restTemplate = new TestRestTemplate();

        // given
        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:8990/hello?name={name}", String.class, "Spring");

        // when & then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        assertThat(response.getBody()).isEqualTo("Hello Spring");
    }

    @Test
    void failHelloApi() {
        TestRestTemplate restTemplate = new TestRestTemplate();

        // given
        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:8990/hello?name=", String.class);

        // when & then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
