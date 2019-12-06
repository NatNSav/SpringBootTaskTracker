package com.gbjavacourse.SpringBootTaskTracker.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@PropertySource("classpath:application.properties")
@ComponentScan("com.gbjavacourse.SpringBootTaskTracker")
public class AppConfig implements WebMvcConfigurer {
}
