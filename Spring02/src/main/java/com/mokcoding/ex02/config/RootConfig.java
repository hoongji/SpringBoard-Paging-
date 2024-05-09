package com.mokcoding.ex02.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// root-context.xml 과 동일
@Configuration
@ComponentScan(basePackages = {"com.mokcoding.ex02.service"})
@MapperScan(basePackages = {"com.mokcoding.ex02.persistence"}) // 패키지 경로롤 Mapper 스캐닝
public class RootConfig {
	
	@Bean // 스프링 Bean으로 설정. xml의 <bean> 태그와 동일 
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig(); 
		config.setDriverClassName("oracle.jdbc.OracleDriver"); // jdbc 드라이버 정보
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		config.setUsername("STUDY");
		config.setPassword("1234");
		
		config.setMaximumPoolSize(10); // 최대 풀(pool) 크기 설정
		config.setConnectionTimeout(30000); // Connection 타임 아웃 설정 (30초)
		HikariDataSource ds = new HikariDataSource(config); 
		// config 객체를 참조하여 DataSource 객체 생성
		
		return ds; // ds 객체 리턴
	}
	
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception { 
		SqlSessionFactoryBean sqlSessionFactoryBean
			= new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
	}
	
	
}//end RootConfig
