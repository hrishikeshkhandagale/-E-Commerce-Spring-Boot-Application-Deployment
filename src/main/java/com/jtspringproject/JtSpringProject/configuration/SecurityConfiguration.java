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

            http.securityMatcher("/admin/**")
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/login").permitAll()
                        .anyRequest().hasRole("ADMIN")
                )
                .formLogin(login -> login
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/loginvalidate")
                        .defaultSuccessUrl("/admin/", true)
                        .failureUrl("/admin/login?error=true")
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/admin/login")
                        .deleteCookies("JSESSIONID")
                )
                .exceptionHandling(e -> e.accessDeniedPage("/403"))
                .csrf(csrf -> csrf.disable());

            return http.build();
        }
    }

    /* ---------------- USER SECURITY ---------------- */
    @Configuration
    @Order(2)
    public static class UserSecurityConfig {

        @Bean
        public SecurityFilterChain userFilterChain(HttpSecurity http) throws Exception {

            http.authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login", "/register", "/newuserregister").permitAll()
                    .requestMatchers("/user/**", "/home", "/buy").hasRole("USER")
                    .anyRequest().authenticated()
            )
            .formLogin(login -> login
                    .loginPage("/login")
                    .loginProcessingUrl("/userloginvalidate")
                    .defaultSuccessUrl("/home", true)
                    .failureUrl("/login?error=true")
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .deleteCookies("JSESSIONID")
            )
            .exceptionHandling(e -> e.accessDeniedPage("/403"))
            .csrf(csrf -> csrf.disable());

            return http.build();
        }
    }

    /* ---------------- USER DETAILS ---------------- */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = UserService.getUserByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found: " + username);
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
