package com.skan.tms.mobile.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * <pre>
 * Class Name  : DataSourceMybatisConfig.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2016. 5. 25.          skan               최초생성
 * </pre>
 *
 * @author skan
 * @since 2016. 5. 25.
 * @version
 *
 * Copyright (C) 2016 by SKAN Corp. All right reserved.
 */
@Configuration
@Lazy
@EnableTransactionManagement
@MapperScan(basePackages={"com.knkcorp.tms.web.mybatis.repository"}
			, sqlSessionFactoryRef="mybatisSqlSessionFactory")
public class DataSourceMybatisConfig implements EnvironmentAware {
	
	@Value("${db.driver}")
	private String DB_DRIVER;
	@Value("${db.password}")
	private String DB_PASSWORD;
	@Value("${db.url}")
	private String DB_URL;
	@Value("${db.username}")
	private String DB_USERNAME;
	
	
	@Bean(name = "mybatisDataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("db.driver"));
		dataSource.setUrl(env.getProperty("db.url"));
		dataSource.setUsername(env.getProperty("db.username"));
		dataSource.setPassword(env.getProperty("db.password"));
		//dataSource.setDriverClassName(DB_DRIVER);
		//dataSource.setUrl(DB_PASSWORD);
		//dataSource.setUsername(DB_URL);
		//dataSource.setPassword(DB_USERNAME);

		return dataSource;
	}
	
	@Bean(name = "transactionManagerMybatis")
	public PlatformTransactionManager transactionManager(@Qualifier("mybatisDataSource") DataSource masterDataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(masterDataSource);
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}
	
	@Bean(name = "mybatisSqlSessionFactory")
	public SqlSessionFactory mybatisSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(this.dataSource());
		
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/knkcorp/tms/web/mybatis/repository/*.xml"));//("classpath:mapper/*.xml"));
		sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis.xml"));
		
		return (SqlSessionFactory) sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception{
		return new SqlSessionTemplate(this.mybatisSqlSessionFactory());
	}
	
	@Autowired
	@Lazy
	private Environment env;

	/* (non-Javadoc)
	 * @see org.springframework.context.EnvironmentAware#setEnvironment(org.springframework.core.env.Environment)
	 */
	@Override
	public void setEnvironment(Environment environment) {
		 env = environment;
	}

}
