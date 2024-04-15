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
