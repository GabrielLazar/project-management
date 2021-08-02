package com.gabriellazar.projectmanagement.config;

import com.gabriellazar.projectmanagement.services.SecurityService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthSuccessHandler authSuccessHandler;
    private SecurityService securityService;

    public SecurityConfig(AuthSuccessHandler authSuccessHandler, SecurityService securityService) {
        this.authSuccessHandler = authSuccessHandler;
        this.securityService = securityService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/administration/**").hasAnyAuthority("Admin","Manager")
                .antMatchers("/manager/**").hasAnyAuthority("Manager")
                .antMatchers("/employee/**").hasAnyAuthority("Employee")
                .antMatchers("/", "/welcome", "/fragments/**", "/assets/**", "/images/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/welcome")
                .successHandler(authSuccessHandler)
                .failureUrl("/login?error=true").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                .userDetailsService(securityService);
    }
}
