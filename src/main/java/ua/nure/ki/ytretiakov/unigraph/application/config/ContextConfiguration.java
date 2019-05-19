package ua.nure.ki.ytretiakov.unigraph.application.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:settings.properties")
@ComponentScan({"ua.nure.ki.ytretiakov.unigraph"})
@EnableJpaRepositories("ua.nure.ki.ytretiakov.unigraph.data")
public class ContextConfiguration {

    private static final String DB_DRIVER_PROPERTY_KEY = "db.driver";
    private static final String DB_SCHEMA_PROPERTY_KEY = "db.schema";
    private static final String DB_USER_PROPERTY_KEY = "db.user";
    private static final String DB_PASSWORD_PROPERTY_KEY = "db.password";
    private static final String DB_URL_PROPERTY_KEY = "db.url";
    private static final String DB_HIBERNATE_DIALECT_PROPERTY_KEY = "db.hibernate.dialect";
    private static final String DB_HIBERNATE_SHOW_SQL_PROPERTY_KEY = "db.hibernate.show_sql";
    private static final String DB_HIBERNATE_HBM2DDL_PROPERTY_KEY = "db.hibernate.hbm2ddl.auto";

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getProperty(DB_DRIVER_PROPERTY_KEY));
        dataSource.setUrl(getProperty(DB_URL_PROPERTY_KEY));
        dataSource.setUsername(getProperty(DB_USER_PROPERTY_KEY));
        dataSource.setPassword(getProperty(DB_PASSWORD_PROPERTY_KEY));
        dataSource.setSchema(getProperty(DB_SCHEMA_PROPERTY_KEY));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaProperties(jpaProperties());
        emf.setPackagesToScan("ua.nure.ki.ytretiakov.unigraph.data.model");
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return emf;
    }

    private Properties jpaProperties() {
        final Properties properties = new Properties();
        properties.put(DB_HIBERNATE_DIALECT_PROPERTY_KEY, getProperty(DB_HIBERNATE_DIALECT_PROPERTY_KEY));
        properties.put(DB_HIBERNATE_SHOW_SQL_PROPERTY_KEY, getProperty(DB_HIBERNATE_SHOW_SQL_PROPERTY_KEY));
        properties.put(DB_HIBERNATE_HBM2DDL_PROPERTY_KEY, getProperty(DB_HIBERNATE_HBM2DDL_PROPERTY_KEY));
        return properties;
    }

    private String getProperty(final String propertyKey) {
        final String propertyValue = environment.getProperty(propertyKey);
        if (propertyValue == null) {
            throw new RuntimeException("No " + propertyKey + " property in settings.properties");
        } else {
            return propertyValue;
        }
    }
}

