package com.skan.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <pre>
 * Class Name  : DataSourceConfig.java
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
 * 			Copyright (C) 2016 by SKAN Corp. All right reserved.
 */

@Configuration
@EnableJpaRepositories(basePackages={ 
										 "com.skan.potal.web.jpa.repository" 
										,"com.skan.potal.web.potal"
										,"org.springframework.social.connect.springdata.jpa"
									}, transactionManagerRef="transactionManager")
@EnableTransactionManagement
public class DataSourceJpaConfig {

	@Value("${db.driver}")
	private String DB_DRIVER;

	@Value("${db.password}")
	private String DB_PASSWORD;

	@Value("${db.url}")
	private String DB_URL;

	@Value("${db.username}")
	private String DB_USERNAME;
	
	@Value("${hibernate.dialect}")
	private String HIBERNATE_DIALECT;

	@Value("${hibernate.show_sql}")
	private String HIBERNATE_SHOW_SQL;

	@Value("${hibernate.hbm2ddl.auto:validate}")
	private String HIBERNATE_HBM2DDL_AUTO;
	
	// 미사용
	@Value("${entitymanager.packagesToScan}")
	private String ENTITYMANAGER_PACKAGES_TO_SCAN;

	@Primary
	@Bean(name = "jpaDataSource")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);
		return dataSource;
	}

	/**
	 * 엔티티로 사용할 Model Package 선언
	 * 
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.skan.potal.web.jpa.dto"
											,"com.skan.potal.web.potal"
											,"org.springframework.social.connect.springdata.jpa" });

		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
	// 하이버네이트 프로퍼티 설정 
	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);// drop-and-create-tables
		hibernateProperties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
		hibernateProperties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
		hibernateProperties.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		//hibernateProperties.setProperty("hibernate.format_sql", "true");
		
		// 배치 정보 
		// 전략 설명 https://vladmihalcea.com/2015/03/18/how-to-batch-insert-and-update-statements-with-hibernate/
		hibernateProperties.setProperty("hibernate.jdbc.batch_size", "100");
		hibernateProperties.setProperty("hibernate.jdbc.batch_versioned_data", "true");
		hibernateProperties.setProperty("hibernate.order_inserts", "true");
		hibernateProperties.setProperty("hibernate.order_updates", "true");
		
		// Connection pool
		hibernateProperties.setProperty("hibernate.c3p0.timeout", "300");
		hibernateProperties.setProperty("hibernate.c3p0.testConnectionOnCheckout" , "true");
        //hibernateProperties.setProperty("hibernate.c3p0.min_size", "1");
        //hibernateProperties.setProperty("hibernate.c3p0.max_size", "15");
        //hibernateProperties.setProperty("hibernate.c3p0.max_statements", "50");
        //hibernateProperties.setProperty("hibernate.c3p0.idle_test_period", "3000");
		
		// properties
		// hibernateProperties.setProperty("hibernate.use_sql_comments", "true");
		// hibernateProperties.setProperty("hibernate.id.new_generator_mappings", "true");
		// hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
		return hibernateProperties;
	}
	
	@Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
}
