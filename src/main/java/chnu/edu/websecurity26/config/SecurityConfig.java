package chnu.edu.websecurity26.config;

import lombok.RequiredArgsConstructor;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/*
 @author Lenovo
 @project web-security26
 @class SecurityConfig
 @version 1.0.0
 @since 22/09/2026 - 12.16
*/
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public static Advisor preAuthorizeMethodInterceptor() {
        return AuthorizationManagerBeforeMethodInterceptor.preAuthorize();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/index.html", "/auth/**").permitAll()
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider);
        // .addFilterBefore()  // TODO

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder().encode("user"))
//                .roles("USER")
//                .build();
//
//        UserDetails superadmin = User.builder()
//                .username("superadmin")
//                .password(passwordEncoder().encode("superadmin"))
//                .roles("SUPERADMIN")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(admin, user, superadmin);
//    }

}