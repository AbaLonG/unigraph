package ua.nure.ki.ytretiakov.unigraph.application.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:settings.properties")
@ComponentScan({"ua.nure.ki.ytretiakov.unigraph"})
@EnableJpaRepositories("ua.nure.ki.ytretiakov.unigraph.data.repository")
@EnableTransactionManagement
public class ContextConfiguration {

    private static final String DB_DRIVER_PROPERTY_KEY = "db.driver";
    private static final String DB_USER_PROPERTY_KEY = "db.user";
    private static final String DB_PASSWORD_PROPERTY_KEY = "db.password";
    private static final String DB_URL_PROPERTY_KEY = "db.url";
    private static final String DB_HIBERNATE_DIALECT_PROPERTY_KEY = "db.hibernate.dialect";
    private static final String DB_HIBERNATE_SHOW_SQL_PROPERTY_KEY = "db.hibernate.show_sql";
    private static final String DB_HIBERNATE_HBM2DDL_PROPERTY_KEY = "db.hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS = "hibernate.enable_lazy_load_no_trans";

    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(getProperty(DB_DRIVER_PROPERTY_KEY));
        dataSource.setUrl(getProperty(DB_URL_PROPERTY_KEY));
        dataSource.setUsername(getProperty(DB_USER_PROPERTY_KEY));
        dataSource.setPassword(getProperty(DB_PASSWORD_PROPERTY_KEY));
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setDatabasePlatform(getProperty(DB_HIBERNATE_DIALECT_PROPERTY_KEY));
        adapter.setGenerateDdl(true);
        adapter.setShowSql(Boolean.valueOf(getProperty(DB_HIBERNATE_SHOW_SQL_PROPERTY_KEY)));
        return adapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setJpaProperties(jpaProperties());
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setPackagesToScan("ua.nure.ki.ytretiakov.unigraph.data.model");
        emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        return emf;
    }

    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(dataSource());
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(200000);
        return resolver;
    }

    private Properties jpaProperties() {
        final Properties properties = new Properties();
        properties.put(DB_HIBERNATE_DIALECT_PROPERTY_KEY, getProperty(DB_HIBERNATE_DIALECT_PROPERTY_KEY));
        properties.put(DB_HIBERNATE_SHOW_SQL_PROPERTY_KEY, getProperty(DB_HIBERNATE_SHOW_SQL_PROPERTY_KEY));
        properties.put(DB_HIBERNATE_HBM2DDL_PROPERTY_KEY, getProperty(DB_HIBERNATE_HBM2DDL_PROPERTY_KEY));
        properties.put(HIBERNATE_ENABLE_LAZY_LOAD_NO_TRANS, true);
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

