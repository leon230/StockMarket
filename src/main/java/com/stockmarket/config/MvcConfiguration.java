package com.stockmarket.config;

import com.stockmarket.dao.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

/**
 * Created by lukasz.homik on 2016-11-04.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.stockmarket")
@Import({ WebSecurityConfig.class })
public class MvcConfiguration extends WebMvcConfigurerAdapter{

    public static String databaseSchema = "awsstock";
//    public static String databaseSchema = "fpstock";
    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
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
//        dataSource.setUrl("jdbc:mysql://localhost:3306/"+databaseSchema);
//        dataSource.setUsername("root");
//        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://" + databaseSchema + ".ciao4vitmcqb.us-west-2.rds.amazonaws.com");
        dataSource.setUsername("admin");
        dataSource.setPassword("admin123");

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
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
//            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/home").allowedOrigins("http://localhost:8080");
            }
        };
    }

}
