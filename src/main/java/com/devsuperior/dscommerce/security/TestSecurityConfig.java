package com.devsuperior.dscommerce.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile(value = "test")
public class TestSecurityConfig {
    
    @Bean
    @Order(value = Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain testFilterChain(HttpSecurity http) throws Exception {
        return http
            .securityMatcher(PathRequest.toH2Console())
            .headers(headers -> headers.frameOptions(options -> options.disable()))
            .csrf(csrf -> csrf.disable())
            .build();
    }

}
