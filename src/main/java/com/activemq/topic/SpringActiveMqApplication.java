package com.activemq.topic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Map;

/**
 * Created by arpitha.vaddu on 03/11/2016.
 */
@SpringBootApplication
public class SpringActiveMqApplication extends SpringBootServletInitializer {

    public static void main(String[] args){
        ApplicationContext ctx =SpringApplication.run(SpringActiveMqApplication.class,args);
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(SpringActiveMqApplication.class);
    }
}
