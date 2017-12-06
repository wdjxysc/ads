package com.jrcx.ads;

import com.jrcx.ads.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Administrator.
 * @date 2017/11/30
 */
//@Configuration
public class SessionConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置拦截器
        registry.addInterceptor(new LoginCheckInterceptor()).addPathPatterns("/**");

        super.addInterceptors(registry);
    }
}
