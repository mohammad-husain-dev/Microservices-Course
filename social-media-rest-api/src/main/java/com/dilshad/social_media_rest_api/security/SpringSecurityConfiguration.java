package com.dilshad.social_media_rest_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
    @Bean
    public SecurityFilterChain  getSpringSecurityFilterChain(HttpSecurity http) throws Exception {
        // filter chain sequence
        //1. All requess should be authenticated
        http.authorizeHttpRequests(
             authorizeRequests -> authorizeRequests.anyRequest().authenticated()
        );

        // 2. If request is not authenticated, a web page is shown
        http.httpBasic(Customizer.withDefaults());

        // 3. CSRF -> POST, PUT
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
