package com.crewmate.shopapicommon.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.crewmate.shopapicommon.properties.ApplicationProperties;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataSourceConfig {
    private final ApplicationProperties applicationProperties;

    @Bean
    public DataSource dataSource() {
        HikariConfig hc = new HikariConfig();
        
        log.info("applicationProperties : {}", applicationProperties);
        
        hc.setJdbcUrl(applicationProperties.getSpring().getDatasource().getHikari().getJdbcUrl()); //스키마를 안적어도 되는데?
        hc.setDriverClassName(applicationProperties.getSpring().getDatasource().getHikari().getDriverClassName());
        hc.setUsername(applicationProperties.getSpring().getDatasource().getHikari().getUsername());
        hc.setPassword(applicationProperties.getSpring().getDatasource().getHikari().getPassword());


        return new HikariDataSource(hc);
    }
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource); //ID, PW, URL, class-name 등의 설정
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml")); //sql 이 있는 xml 파일 선언 
        sessionFactory.setTypeAliasesPackage("com.crewmate.*.model"); //parameter, result type의 alias 정의
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true); //카멜 케이스 정의

        return sessionFactory.getObject();
    }
}
