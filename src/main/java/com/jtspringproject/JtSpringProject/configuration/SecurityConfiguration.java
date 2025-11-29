package com.jtspringproject.JtSpringProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jtspringproject.JtSpringProject.models.User;
import com.jtspringproject.JtSpringProject.services.userService;

@Configuration
public class SecurityConfiguration {

    private final userService UserService;

    public SecurityConfiguration(userService UserService) {
        this.UserService = UserService;
    }

    /* ---------------- ADMIN SECURITY ---------------- */
    @Configuration
    @Order(1)
    public static class AdminSecurityConfig {

        @Bean
        public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {

            http.antMatcher("/admin/**")
                .authorizeRequests()
                .requestMatchers(new AntPathRequestMatcher("/admin/login")).permitAll()
                .anyRequest().hasRole("ADMIN")
                .and()
                .formLogin()
                    .loginPage("/admin/login")
                    .loginProcessingUrl("/admin/loginvalidate")
                    .defaultSuccessUrl("/admin/index", true)
                    .failureUrl("/admin/login?error=true")
                .and()
                .logout()
                    .logoutUrl("/admin/logout")
                    .logoutSuccessUrl("/admin/login")
                    .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling().accessDeniedPage("/403");

            http.csrf().disable();
            return http.build();
        }
    }

    /* ---------------- USER SECURITY ---------------- */
    @Configuration
    @Order(2)
    public static class UserSecurityConfig {

        @Bean
        public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {

            http.authorizeRequests()
                .requestMatchers(
                        new AntPathRequestMatcher("/login"),
                        new AntPathRequestMatcher("/register"),
                        new AntPathRequestMatcher("/newuserregister")
                ).permitAll()
                .anyRequest().hasRole("USER")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/userloginvalidate")
                    .defaultSuccessUrl("/user/products", true)
                    .failureUrl("/login?error=true")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling().accessDeniedPage("/403");

            http.csrf().disable();
            return http.build();
        }
    }

    /* ---------------- USER DETAILS ---------------- */
    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            User user = UserService.getUserByUsername(username);
            if (user == null) throw new UsernameNotFoundException(username);

            String role = user.getRole().equals("ROLE_ADMIN") ? "ADMIN" : "USER";

            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password(user.getPassword())
                    .roles(role)
                    .build();
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
