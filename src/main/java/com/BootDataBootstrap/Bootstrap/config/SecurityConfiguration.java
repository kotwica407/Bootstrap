package com.BootDataBootstrap.Bootstrap.config;

import com.BootDataBootstrap.Bootstrap.dao.UsersRepository;
import com.BootDataBootstrap.Bootstrap.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(new BCryptPasswordEncoder(11));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/*task*").hasAnyRole("ADMIN","USER")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/app-login")
                    .usernameParameter("app_username")
                    .passwordParameter("app_password")
                    .defaultSuccessUrl("/all-tasks")
                .and().logout()
                    .logoutUrl("/app-logout")
                    .logoutSuccessUrl("/login")
                    .and().exceptionHandling()
                        .accessDeniedPage("/error");
    }
}
