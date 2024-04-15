package com.springbootac;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootApplication {

	/**
	 * Auto Configuration 이 뭐가 적용되었는지 확인하는 method
	 * - 모든 condition 의 기록들이 map 형태로 출력됨.
	 * - 결과를 필터링하기 위해 .filter 을 사용한다.
	 *
	 * .getValue().isFullMatch())
	 * 		의미: condition 을 통과한 것
	 * !condition.getKey().contains("Jmx")
	 * 		의미: "Jmx" 와 관련된 것은 출력에서 제외한다.
	 * condition.getValue().forEach(c-> ... c.getOutcome())
	 * 		의미: condition.getKey() 에 해당하는 클래스나 메소드가 어떤 조건을 통과했는가 확인
	 *
	 */
	/**
	 * spring-boot-starter-web : web 을 추가한 이후에 추가된 Auto Configuration
	 *
	 * ...autoconfigure.http.HttpMessageConvertersAutoConfiguration
	 * 		http 로 통신할 때 body 관련한 내용들 (json 처리)
	 * ...autoconfigure.jackson.JacksonAutoConfiguration
	 * 			$JacksonObjectMapperConfiguration
	 * 			#jacksonObjectMapper
	 * 		jackson 라이브러리
	 * ...autoconfigure.web.client.RestClientAutoConfiguration
	 * 		RestTemplate 의 Builder Bean 을 제공해준다.
	 * 		SpringBoot 에서 RestTemplate 을 사용하려면 RestTemplateBuilder 을 받아서 추가 설정을 하고 이 Bean 을 사용하면 된다.
	 * ...autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration
	 * 		web server 구성 정보 (Tomcat 등등...)
	 * 		@EnableConfigurationProperties(ServerProperties.class)
	 * 			property 값 설정 내용
	 *...autoconfigure.web.servlet.DispatcherServletAutoConfiguration
	 * 		DispatcherServlet 구성 정보
	 * ...autoconfigure.web.servlet.HttpEncodingAutoConfiguration
	 * 		Http 통신 시, 인코딩 관련 구성 정보
	 * ...autoconfigure.web.servlet.MultipartAutoConfiguration
	 * 		Http 통신 시, Multipart 관련 구성 정보
	 *
	 * 	등등...
	 */
	/**
	 * spring-boot-starter-jdbc : jdbc 를 추가한 이후에 추가된 Auto Configuration
	 *
	 * ...autoconfigure.dao.PersistenceExceptionTranslationAutoConfiguration
	 * 		ExceptionTranslation 이 발생하면 PostProcessor 등록
	 * ...autoconfigure.jdbc.DataSourceAutoConfiguration
	 * 		DataSource 자동 구성
	 * 		(driverClassName 은 대부분 이미 들어가 있다)
	 * ...autoconfigure.jdbc.DataSourceConfiguration
	 * 			$Hikari
	 * 		Hikari 구성 정보
	 * ...autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
	 * 		TransactionManager 구성 정보
	 * ...autoconfigure.jdbc.JdbcTemplateAutoConfiguration
	 * 		JdbcTemplate 구성 정보
	 * ...autoconfigure.sql.init.SqlInitializationAutoConfiguration
	 * 		Jdbc 를 사용할 때 Database 를 초기화 하거나, 데이터를 insert 하거나, 정리하는 기능
	 * ...autoconfigure.transaction.TransactionAutoConfiguration
	 * 		PlatformTransactionManager 구성 정보
	 * 		(TransactionTemplate 이 이미 구성되어 있음)
	 */
	@Bean
	ApplicationRunner run(ConditionEvaluationReport report) {
		return args -> {
			report.getConditionAndOutcomesBySource().entrySet().stream()
					.filter(condition -> condition.getValue().isFullMatch())
					.filter(condition -> !condition.getKey().contains("Jmx"))
					.forEach(condition -> {
						System.out.println(condition.getKey());
						condition.getValue().forEach(c->{
							System.out.println("\t통과한 condition: " + c.getOutcome());
						});
						System.out.println();
					});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}
