package com.cosmeticosespacol.GestaoDeEstoque.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Profile("test")
    public SecurityFilterChain h2SecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher(PathRequest.toH2Console()).csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
            auth.requestMatchers("/auth/**").permitAll();
            auth.requestMatchers("/usuario/**").hasRole("ADMINISTRADOR");
            auth.requestMatchers(HttpMethod.GET, "/produto/**").authenticated();
            auth.requestMatchers(HttpMethod.POST, "/produto/**").hasAnyRole("ADMINISTRADOR", "GERENTE");
            auth.requestMatchers(HttpMethod.PUT, "/produto/**").hasAnyRole("ADMINISTRADOR", "GERENTE");
            auth.requestMatchers(HttpMethod.PATCH, "/produto/**").hasAnyRole("ADMINISTRADOR", "GERENTE");
            auth.requestMatchers(HttpMethod.DELETE, "/produto/**").hasAnyRole("ADMINISTRADOR", "GERENTE");
            auth.requestMatchers("/swagger-ui/**").permitAll();
            auth.requestMatchers("/v3/**").permitAll();
            auth.anyRequest().authenticated();
        });
        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
