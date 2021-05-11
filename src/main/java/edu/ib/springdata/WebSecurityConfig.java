package edu.ib.springdata;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/getToken").permitAll().antMatchers("/customer/*").hasRole("CUSTOMER")
                .antMatchers("/admin/*").hasRole("ADMIN")
                .and().addFilter(new JwtFilter(authenticationManager()));
    }
}
