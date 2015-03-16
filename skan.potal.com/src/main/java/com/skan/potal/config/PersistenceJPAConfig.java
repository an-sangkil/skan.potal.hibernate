package com.skan.potal.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <pre>
 * Class Name  : JPAConfig.java
 * Description : 
 * Modification Information
 *
 *    수정일　　　 　　  수정자　　　     수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2014. 12. 9.        ask               최초생성
 * </pre>
 *
 * @author ask
 * @since 2014. 12. 9.
 * @version 
 *
 * Copyright (C) 2014 by SKAN.COMPANY All right reserved.
 */
@Configuration
@EnableTransactionManagement
//@PropertySource({ "classpath:persistence-mysql.properties", "classpath:persistence-postgresql.properties",})
@ComponentScan({ "com.skan.potal" })
@EnableJpaRepositories(basePackages={"com.skan.potal.hibernate.dao","com.skan.potal.web"})
public class PersistenceJPAConfig {
	
	@Autowired private Environment env;
	//@Autowired private DataSourceConfig dataSourceConfig;

    public PersistenceJPAConfig() {
        super();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.skan.potal.hibernate.model","com.skan.potal.web.potal"});

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }
    
    @Bean
	public DataSource dataSource (){
		
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
		driverManagerDataSource.setUrl("jdbc:postgresql://127.0.0.1:5432/potaldb");
		driverManagerDataSource.setUsername("skan");
		driverManagerDataSource.setPassword("1111");
		
		return driverManagerDataSource; 
	}

    @Bean
    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");//drop-and-create-tables
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgresPlusDialect");
        // hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
        return hibernateProperties;
    }
}
