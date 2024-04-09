package toby.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import toby.annotation.ConditionalMyOnClass;
import toby.annotation.EnableMyConfigurationProperties;
import toby.annotation.MyAutoConfiguration;

import javax.sql.DataSource;
import java.sql.Driver;


/**
 * @EnableMyConfigurationProperties
 * property 값을 매개변수로 받아오기
 *      여기선 MyDataSourceProperties class 의 값을 받아온다.
 * @EnableTransactionManagement
 *      AOP(Aspect Oriented programming 관점지향프로그래밍) 와 관련된 기능
 *
 */
@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
@EnableTransactionManagement
public class DataSourceConfig {

    /**
     * h2 설정
     *
     * @ConditionalMyOnClass
     *      해당 class 가 있는지 존재 여부를 확인한다.
     *      (여기선 HikariDataSource 존재 여부를 확인한다.)
     * @ConditionalOnMissingBean
     *      동명의 bean 이 없으면, 현재 등록한 bean 을 사용하도록 한다.
     */
    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    @ConditionalOnMissingBean
    DataSource dataSource (MyDataSourceProperties properties) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        try {
            dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
            dataSource.setUrl(properties.getUrl());
            dataSource.setUsername(properties.getUsername());
            dataSource.setPassword(properties.getPassword());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return dataSource;
    }

    /**
     * hikari 설정
     */
    @Bean
    @ConditionalOnMissingBean
    DataSource hikariDataSource(MyDataSourceProperties properties) {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        return dataSource;
    }

    /**
     * @ConditionalOnSingleCandidate
     *      bean method 가 실행이 될 때
     *      이미 spring-container 의 bean 구성 정보에 이 DataSource.class 타입의 bean 이
     *      딱 한개만 등록되어 있다면, 해당 dataSource 를 가져와서 사용한다는 뜻
     */
    @Bean
    @ConditionalOnSingleCandidate(DataSource.class)
    @ConditionalOnMissingBean
    JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @ConditionalOnSingleCandidate(DataSource.class)
    @ConditionalOnMissingBean
    JdbcTransactionManager jdbcTransactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }

}
