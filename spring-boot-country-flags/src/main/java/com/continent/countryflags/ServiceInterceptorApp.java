package com.continent.countryflags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Component
public class ServiceInterceptorApp extends WebMvcConfigurerAdapter
{
    @Autowired
    InterceptorTrace interceptorTrace;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	registry.addInterceptor(interceptorTrace);
    }
}