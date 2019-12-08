package com.gbjavacourse.SpringBootTaskTracker.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.gbjavacourse.SpringBootTaskTracker")
public class AppConfig implements WebMvcConfigurer {
}
