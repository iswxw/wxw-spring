package com.wxw.manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @ Author ：wxw.
 * @ Date ： 13:59 2020/7/17
 * @ Description：安全配置
 * @ Version:   v_0.0.1
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .mvcMatchers(HttpMethod.GET, "/api/login1")
            .authenticated()
            .and()
            .formLogin()
            .loginPage("/login.html")
            .and()
            .csrf()
            .disable();
    }
}
