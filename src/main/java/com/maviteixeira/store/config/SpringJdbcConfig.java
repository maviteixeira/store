package com.maviteixeira.store.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringJdbcConfig {

    @Bean
    DataSource postgresDataSource() {
        return new DriverManagerDataSource(
            "jdbc:postgresql://localhost:5432/postgres",
            "user",
            "password"
        );
    }

    @Bean
    public FilterRegistrationBean loggingFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns("/*/");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
