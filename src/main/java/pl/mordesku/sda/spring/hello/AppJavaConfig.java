package pl.mordesku.sda.spring.hello;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("pl.mordesku.sda.spring.hello")
@EnableWebMvc
@EnableWebSecurity
@EnableTransactionManagement
public class AppJavaConfig extends WebMvcConfigurationSupport {
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/jsp/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(restDataSource());
        sessionFactory.setPackagesToScan(new String[] { "pl.mordesku.sda.spring.hello.entities" });
                 sessionFactory.setHibernateProperties(new Properties(){
              {
                  setProperty("hibernate.hbm2ddl.auto", "update");
                  setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
                  setProperty("hibernate.globally_quoted_identifiers", "true");
              }
            });
        return sessionFactory;
    }

    @Bean
    public DataSource restDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUrl("jdbc:postgresql://192.168.10.128:5432/kb");
        dataSource.setSchema("public");
        dataSource.setUsername("mordesku");
        dataSource.setPassword("haslo.123");
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new DbAuthenticationManager();
    }
}
