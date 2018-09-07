package filmclub.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("filmclub")
@EnableJpaRepositories("filmclub")
@EnableTransactionManagement
public class SpringConfiguration {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter vendorAdapter, Properties additionalProperties) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("filmclub");
        em.setJpaProperties(additionalProperties);
        em.setJpaVendorAdapter(vendorAdapter);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Configuration
    @Profile("default")
    public static class DefaultSpringConfiguration {
        @Bean
        public DataSource dataSource(){
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl(System.getenv("JDBC_DATABASE_URL"));
            dataSource.setUsername(System.getenv("JDBC_DATABASE_USERNAME"));
            dataSource.setPassword(System.getenv("JDBC_DATABASE_PASSWORD"));
            return dataSource;
        }

        @Bean
        public JpaVendorAdapter jpaVendorAdapter() {
            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            vendorAdapter.setShowSql(false);
            vendorAdapter.setGenerateDdl(false);
            vendorAdapter.setDatabase(Database.POSTGRESQL);
            return vendorAdapter;
        }

        @Bean
        public Properties additionalProperties() {
            Properties properties = new Properties();
            properties.setProperty("hibernate.hbm2ddl.auto", "validate");
            return properties;
        }
    }

    @Configuration
    @Profile("local")
    public static class LocalSpringConfiguration {
        @Bean
        public DataSource dataSource(){
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.postgresql.Driver");
            dataSource.setUrl(System.getenv("filmclub.postgres.url"));
            dataSource.setUsername(System.getenv("filmclub.postgres.username"));
            dataSource.setPassword(System.getenv("filmclub.postgres.password"));
            return dataSource;
        }

        @Bean
        public JpaVendorAdapter jpaVendorAdapter() {
            HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            vendorAdapter.setShowSql(true);
            vendorAdapter.setDatabase(Database.POSTGRESQL);
            return vendorAdapter;
        }

        @Bean
        public Properties additionalProperties() {
            Properties properties = new Properties();
            properties.setProperty("hibernate.hbm2ddl.auto", "update");
            //https://stackoverflow.com/questions/10075081/hibernate-slow-to-acquire-postgres-connection
            properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults","false");
            return properties;
        }
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }


}
