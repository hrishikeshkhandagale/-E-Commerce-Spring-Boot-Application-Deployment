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

    userService UserService;

    public SecurityConfiguration(userService UserService) {
        this.UserService = UserService;
    }

    // 🔹 ADMIN SECURITY
    @Configuration
    @Order(1)
    public static class AdminConfigurationAdapter {

        @Bean
        SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**")
                .authorizeHttpRequests(req -> req
                    .requestMatchers("/admin/login").permitAll()
                    .anyRequest().hasRole("ADMIN")
                )
                .formLogin(login -> login
                    .loginPage("/admin/login")
                    .loginProcessingUrl("/admin/loginvalidate")
                    .successHandler((request, response, authentication) -> response.sendRedirect("/admin/index"))
                    .failureUrl("/admin/login?error=true")
                )
                .logout(logout -> logout
                    .logoutUrl("/admin/logout")
                    .logoutSuccessUrl("/admin/login")
                    .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/403"));

            http.csrf(csrf -> csrf.disable());
            return http.build();
        }
    }

    // 🔹 USER SECURITY
    @Configuration
    @Order(2)
    public static class UserConfigurationAdapter {

        @Bean
        SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(req -> req
                    .requestMatchers("/login", "/register", "/newuserregister").permitAll()
                    .anyRequest().hasRole("USER")
                )
                .formLogin(login -> login
                    .loginPage("/login")
                    .loginProcessingUrl("/userloginvalidate")
                    .successHandler((request, response, authentication) -> response.sendRedirect("/user/products"))
                    .failureUrl("/login?error=true")
                )
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(ex -> ex.accessDeniedPage("/403"));

            http.csrf(csrf -> csrf.disable());
            return http.build();
        }
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            User user = UserService.getUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username " + username + " not found.");
            }
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
