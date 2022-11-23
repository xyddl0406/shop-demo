package com.crewmate.shopcommonapi.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        HikariConfig hc = new HikariConfig();
        hc.setJdbcUrl("jdbc:mysql://localhost:3306?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC"); //스키마를 안적어도 되는데?
        hc.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hc.setUsername("root");
        hc.setPassword("0000");
        
        return new HikariDataSource(hc); //DataSourceBuilder.create().build()
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
