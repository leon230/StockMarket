package com.stockmarket.config;

import com.stockmarket.dao.*;
import com.stockmarket.service.StockService;
import com.stockmarket.service.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

/**
 * Created by lukasz.homik on 2016-11-04.
 */

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.stockmarket")
@Import({ WebSecurityConfig.class })
@PropertySource(value = "classpath:messages.properties")
public class MvcConfiguration extends WebMvcConfigurerAdapter{

    @Autowired
    private Environment environment;

    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix(environment.getRequiredProperty("page.prefix"));
        resolver.setSuffix(environment.getRequiredProperty("page.suffix"));
        return resolver;
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean public ResourceBundleMessageSource messageSource(){
        ResourceBundleMessageSource ResourceBundleMessageSource = new ResourceBundleMessageSource();
        ResourceBundleMessageSource.setBasename("messages");
        return ResourceBundleMessageSource;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://awsstock.ciao4vitmcqb.us-west-2.rds.amazonaws.com:3306/awsstock");
        dataSource.setUrl(environment.getRequiredProperty("db.connectionURL"));
        dataSource.setUsername(environment.getRequiredProperty("db.username"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));

        return dataSource;
    }
    @Bean
    public UserDAO getUserDAO() {
        return new UserDAOImpl(getDataSource());
    }
    @Bean
    public WalletDAO getWalletDAO() {
        return new WalletDAOImpl(getDataSource());
    }
    @Bean
    public StockDAO getStockDAO() {
        return new StockDAOImpl(getDataSource());
    }
    @Bean
    public StockService stockService() {return new StockServiceImpl(getDataSource());}
    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(getDataSource());
    }
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
////            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/home").allowedOrigins("http://localhost:8080");
//            }
//        };
//    }

}
