package com.jtspringproject.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    // 1) USER + ROLE DB मधून घ्यायचे (CUSTOMER table)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            // CUSTOMER टेबल मधून username, password, enabled
            .usersByUsernameQuery(
                "SELECT username, password, true as enabled FROM CUSTOMER WHERE username = ?")
            // role कॉलम मधून authority (ROLE_ADMIN / ROLE_NORMAL)
            .authoritiesByUsernameQuery(
                "SELECT username, role FROM CUSTOMER WHERE username = ?")
            .passwordEncoder(passwordEncoder());
    }

    // 2) कोणते URL कोणाला allow करायचे
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // JSP app साठी साधेपणाने disable ठेवतो
            .authorizeRequests()
                // static resources allow
                .antMatchers("/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                // register आणि home वगैरे public
                .antMatchers("/", "/home", "/register", "/doRegister", "/error").permitAll()
                // admin area फक्त ADMIN ला
                .antMatchers("/admin/**").hasRole("ADMIN")
                // बाकी सर्व authenticated
                .anyRequest().authenticated()
            .and()
                // 3) custom login page (/login)
                .formLogin()
                    .loginPage("/login")              // controller मधील /login mapping
                    .loginProcessingUrl("/dologin")   // form action असेल तर
                    .usernameParameter("username")    // form मधील input name
                    .passwordParameter("password")    // form मधील input name
                    .defaultSuccessUrl("/", true)     // successful login नंतर कुठे जायचे
                    .permitAll()
            .and()
                // 4) logout
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll();
    }

    // 5) plain text password (123, 765) match करण्यासाठी
    @Bean
    public PasswordEncoder passwordEncoder() {
        // demo/project साठी चालेल; production ला वापरू नका
        return NoOpPasswordEncoder.getInstance();
    }
}
