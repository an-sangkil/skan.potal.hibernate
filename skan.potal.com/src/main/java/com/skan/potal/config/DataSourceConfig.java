package com.skan.potal.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//@Configuration
public class DataSourceConfig {
	

	private final static String JDBC_CONFIG_PATH = "jdbc.xml";
	
	@Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer pp = new PropertyPlaceholderConfigurer();
        pp.setLocations(new Resource[]{ new ClassPathResource(JDBC_CONFIG_PATH)});
        return pp;
    }
	@Bean
	public DataSource dataSource (){
		
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("org.postgresql.Driver");
		basicDataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/potaldb");
		basicDataSource.setUsername("skan");
		basicDataSource.setPassword("1111");
		basicDataSource.setDefaultAutoCommit(false);
		
		return basicDataSource; 
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource());
		
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/dongbu/potal/web/potal/*.xml"));//("classpath:mapper/*.xml"));
		sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis.xml"));
		
		
		return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception{
		return new SqlSessionTemplate(this.sqlSessionFactory());
	}
	
	
}
