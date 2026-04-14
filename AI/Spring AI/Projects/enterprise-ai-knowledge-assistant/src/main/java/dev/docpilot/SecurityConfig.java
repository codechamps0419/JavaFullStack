/*
@author CodeChamps0419
 */
package dev.docpilot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService users(PasswordEncoder encoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("aprajita")
                        .password(encoder.encode("aprajita123!"))
                        .roles("EMPLOYEE", "ENGINEERING")
                        .build(),
                User.withUsername("hrushikesh")
                        .password(encoder.encode("hrushikesh123!"))
                        .roles("EMPLOYEE", "ENGINEERING")
                        .build(),
                User.withUsername("rekha")
                        .password(encoder.encode("rekha123!"))
                        .roles("EMPLOYEE", "HR")
                        .build(),
                User.withUsername("admin")
                        .password(encoder.encode("admin123!"))
                        .roles("ADMIN", "ENGINEERING", "HR")
                        .build());
    }

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        return http
                // CSRF is disabled for this stateless Basic Auth demo.
                // Use an appropriate CSRF strategy for cookie-based sessions.
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/favicon.ico", "/actuator/health")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/documents")
                        .hasAnyRole("EMPLOYEE", "ADMIN")
                        .requestMatchers("/api/**")
                        .authenticated()
                        .anyRequest()
                        .denyAll())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
