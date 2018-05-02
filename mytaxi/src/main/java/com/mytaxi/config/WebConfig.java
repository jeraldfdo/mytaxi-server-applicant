package com.mytaxi.config;

import com.mytaxi.util.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This class is responsible for Web configuration.
 * @author jeraldfdo
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter
{

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    }

}
