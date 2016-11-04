package com.stockmarket.config;

import javax.sql.DataSource;

import com.stockmarket.dao.UserDAO;
import com.stockmarket.dao.UserDAOImpl;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by lukasz.homik on 2016-11-04.
 */

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.stockmarket")
@Import({ WebSecurityConfig.class })
public class MvcConfiguration extends WebMvcConfigurerAdapter{

    @Bean
    public ViewResolver getViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
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
        dataSource.setUrl("jdbc:mysql://localhost:3306/ticketsystem");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        return dataSource;
    }
    @Bean
    public UserDAO getUserDAO() {
        return new UserDAOImpl(getDataSource());
    }

}
