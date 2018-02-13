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

@Configuration  // ta adnotacja informuje springa że ma doczynienia z klasą konfigurującą kontekst
@ComponentScan("pl.mordesku.sda.spring.hello") // tą adnotacją mówimy springowi od jakiego pakieetu począwszy ma skanować w poszukiwaniu adnotowanych klas (@Component, @Repository itp.)
@EnableWebMvc //uruchamiamy obsługę spring mvc
@EnableWebSecurity//konfigurujemy wsparcie dla spring security
@EnableTransactionManagement //właczamy zarządzanie transakcjami
public class AppJavaConfig extends WebMvcConfigurationSupport {
    //definiujemy beana
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
        sessionFactory.setPackagesToScan("pl.mordesku.sda.spring.hello.entities");
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
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        dataSource.setSchema("public");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");
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
