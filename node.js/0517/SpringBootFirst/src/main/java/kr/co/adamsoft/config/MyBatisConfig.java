package kr.co.adamsoft.config;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"kr.co.adamsoft.mapper"})
public class MyBatisConfig {
//	@Bean
//	public DataSource batisDataSource() {
//		HikariConfig hikariConfig = new HikariConfig();
//	}
}
