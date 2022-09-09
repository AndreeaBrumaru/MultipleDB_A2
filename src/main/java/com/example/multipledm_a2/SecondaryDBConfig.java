package com.example.multipledm_a2;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "secondaryEntityManagerFactory",
        transactionManagerRef = "secondaryTransactionManager",
        basePackages = { "com.example.multipledm_a2.secondary" })
public class SecondaryDBConfig {

    @Autowired
    private Environment env;

    @Bean(name="secondaryDataSource")
//    @ConfigurationProperties(prefix="spring.seconddatasource")
    public DataSource secondaryDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty("spring.seconddatasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.seconddatasource.jdbc-url"));
        dataSource.setUsername(env.getProperty("spring.seconddatasource.username"));
        dataSource.setPassword(env.getProperty("spring.seconddatasource.password"));

        return dataSource;
    }

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                                @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty("spring.jpa.hibernate.ddl-auto"));
        return builder
                .dataSource(secondaryDataSource)
                .properties(properties)
                .packages("com.example.multipledm_a2.secondary")
                .build();
    }

//    @Bean(name = "secondaryContainerEntityManagerFactoryBean")
//    public LocalContainerEntityManagerFactoryBean secondaryContainerEntityManagerFactoryBean() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(secondaryDataSource());
////        em.setPersistenceUnitName(SECONDARY_PERSISTENCE_UNIT_NAME);
//        em.setPackagesToScan(SECONDARY_DATASOURCE_BASE_PACKAGE_TO_SCAN);
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put(AvailableSettings.HBM2DDL_AUTO, dllAuto);
//        em.setJpaPropertyMap(properties);
//        return em;
//    }
//    @Bean(name = "secondaryTransactionManager")
//    public PlatformTransactionManager secondaryTransactionManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(
//                secondaryContainerEntityManagerFactoryBean().getObject());
//        return transactionManager;
//    }

    @Bean(name = "secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryEntityManagerFactory") EntityManagerFactory secondaryEntityManagerFactory) {
        return new JpaTransactionManager(secondaryEntityManagerFactory);
    }
}
