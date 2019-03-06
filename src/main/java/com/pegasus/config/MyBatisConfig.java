package com.pegasus.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan("com.pegasus.dao")
@PropertySource({"classpath:datasource.yml"})
public class MyBatisConfig {

	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		
		return DataSourceBuilder.create().type(DruidDataSource.class).build();
	}
}
