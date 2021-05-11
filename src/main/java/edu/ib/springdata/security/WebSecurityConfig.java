package edu.ib.springdata.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/getToken").permitAll()
                .antMatchers("/api/customer/**").hasRole("CUSTOMER")
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .and().addFilter(new JwtFilter(authenticationManager())).httpBasic().and().csrf().disable();


    }
}
