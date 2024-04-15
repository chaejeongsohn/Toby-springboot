package toby.helloboot;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * InitializingBean
 * 		모든 프로퍼티 세팅까지 다 끝나면 뭘 실행할지 코드 구현할 때 이용한다.
 * 		Lifecycle Interface 를 spring 이 호출해준다.
 */
@SpringBootApplication
public class HelloBootApplication{
	private final JdbcTemplate jdbcTemplate;

	public HelloBootApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * @PostConstruct
     * 		자바 표준 메소드
	 * 		Lifecycle Interface 를 이용한 방식을 간결하게 대체할 수 있는 용도로 사용됨
	 */
	@PostConstruct
	void init() {
		jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloBootApplication.class, args);
	}
}
