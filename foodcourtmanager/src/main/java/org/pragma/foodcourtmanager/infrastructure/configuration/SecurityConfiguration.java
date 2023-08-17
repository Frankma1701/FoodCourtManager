package org.pragma.foodcourtmanager.infrastructure.configuration;

import lombok.RequiredArgsConstructor;
import org.pragma.foodcourtmanager.application.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration{

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        //noinspection removal
        httpSecurity.cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/restaurant/").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/dish/").hasRole("OWNER")
                        .requestMatchers(HttpMethod.GET, "/dish/").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/order/").hasRole("CUSTOMER")
                        .requestMatchers(HttpMethod.GET , "/order/**").permitAll()
                        .requestMatchers(HttpMethod.PUT , "/order/").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT , "/order/order-ready").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT , "/order/delivery-order").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT , "/order/cancel-order").hasRole("EMPLOYEE")

                        .requestMatchers("/employee-restaurant/**" ).permitAll()

                        // .requestMatchers("/employee-restaurant/**" ).hasRole("EMPLOYEE")

                        .requestMatchers(HttpMethod.PUT, "/dish/").hasRole("OWNER")
                        .requestMatchers(HttpMethod.GET, "/restaurant/").hasRole("CUSTOMER")
                        .anyRequest().denyAll()
                );
        return httpSecurity.build();
    }


}
