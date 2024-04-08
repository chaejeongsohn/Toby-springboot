package toby.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import toby.annotation.ConditionalMyOnClass;
import toby.annotation.EnableMyConfigurationProperties;
import toby.annotation.MyAutoConfiguration;

import javax.sql.DataSource;
import java.sql.Driver;


/**
 * @EnableMyConfigurationProperties
 * property 값을 매개변수로 받아오기
 *      여기선 MyDataSourceProperties class 의 값을 받아온다.
 */
@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations")
@EnableMyConfigurationProperties(MyDataSourceProperties.class)
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

}
