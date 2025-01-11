package com.kmu.anki.backend.global.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url:NOT SET}")
    private String datasourceUrl;

    @Value("${spring.datasource.username:NOT SET}")
    private String datasourceUsername;

    @Value("${spring.datasource.password:NOT SET}")
    private String datasourcePassword;

    @Value("${spring.datasource.driver-class-name:NOT SET}")
    private String driverClassName;

    @PostConstruct
    public void logDataSourceProperties() {
        System.out.println("=======================================");
        System.out.println("Spring Datasource Configuration:");
        System.out.println("URL: " + datasourceUrl);
        System.out.println("Username: " + datasourceUsername);
        System.out.println("Password: " + datasourcePassword);
        System.out.println("Driver Class Name: " + driverClassName);
        System.out.println("=======================================");
    }
}