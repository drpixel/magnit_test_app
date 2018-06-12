package ru.magnit.test_app;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Конфигурация для доступа к Oracle 1
 *
 * @author Dima Pixel
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactory",
        basePackages = {"ru.magnit.test_app.dao.oracle_1"}
)
public class Oracle1Config {

    @Autowired
    private Environment env;

    /**
     * Метод конфигурации dataSource
     *
     * @return DataSource Источник данных СУБД 1 по настройкам из
     * application.properties
     */
    @Primary
    @Bean(name = "dataSource")
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db1.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("db1.datasource.url"));
        dataSource.setUsername(env.getProperty("db1.datasource.username"));
        dataSource.setPassword(env.getProperty("db1.datasource.password"));

        return dataSource;
    }

    /**
     * Метод конфигурации entityManagerFactory
     *
     * @param builder
     * @param dataSource
     * @return LocalContainerEntityManagerFactoryBean Менеджер работы с
     * сущностями (моделью ru.magnit.test_app.model) СУБД 
     */
    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
            entityManagerFactory(
                    EntityManagerFactoryBuilder builder,
                    @Qualifier("dataSource") DataSource dataSource
            ) {
        return builder
                .dataSource(dataSource)
                .packages("ru.magnit.test_app.model")
                .persistenceUnit("oracle_1")
                .build();
    }

    /**
     * Метод конфигурации transactionManager
     *
     * @param entityManagerFactory
     * @return PlatformTransactionManager Менеджер транзакций
     */
    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
