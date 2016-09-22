package com.shedhack.filter.requestid;

import com.google.gson.Gson;
import com.shedhack.exception.controller.spring.config.EnableExceptionController;
import com.shedhack.spring.actuator.config.EnableActuatorsAndInterceptors;
import com.shedhack.spring.actuator.interceptor.ActuatorTraceRequestInterceptor;
import com.shedhack.trace.request.filter.DefaultTraceRequestInterceptor;
import com.shedhack.trace.request.filter.RequestTraceFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Test Application
 */
@SpringBootApplication
@EnableExceptionController
@EnableActuatorsAndInterceptors
public class Application {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Autowired
    private ActuatorTraceRequestInterceptor actuatorTraceRequestInterceptor;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();

        RequestTraceFilter traceFilter = new RequestTraceFilter(appName, Arrays.asList(
           new DefaultTraceRequestInterceptor(gson()),  actuatorTraceRequestInterceptor
        ));

        bean.setFilter(traceFilter);
        return bean;
    }


}
