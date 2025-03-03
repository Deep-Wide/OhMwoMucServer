package net.ohmwomuc.core.security;

import jakarta.servlet.http.HttpServletResponse;
import net.ohmwomuc.core.exception.CustomExceptionHandler;
import net.ohmwomuc.core.security.filter.JwtAuthenticationFilter;
import net.ohmwomuc.core.security.filter.JwtVerifyFilter;
import net.ohmwomuc.core.security.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

import static net.ohmwomuc.core.exception.CustomExceptionCode.USER_FORBIDDEN;
import static net.ohmwomuc.core.exception.CustomExceptionCode.USER_UNAUTHORIZED;

@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig {

    private final List<String> allowedRequestUrlList = List.of(
            "/api/security/login",
            "/api/security/login-user",
            "/api/muamuc/**",
            "/api/comment/**",
            "/api/likes/**",
            "/api/files/**",
            "/api/users/**",
            "/api/report/title",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtAuthenticationFilter jwtAuthenticationFilter,
                                           JwtVerifyFilter jwtVerifyFilter
                                           ) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers(allowedRequestUrlList.toArray(String[]::new))
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(formLogin -> formLogin.disable())
                .addFilterAt(jwtVerifyFilter, BasicAuthenticationFilter.class)
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(configurationSource()))
                .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(ex -> ex.accessDeniedHandler(getAccessDeniedHandler())
                        .authenticationEntryPoint(getAuthenticationEntryPoint()))
                .logout(logout -> logout.logoutUrl("/api/security/logout"));

        return http.build();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
       return new JwtAuthenticationFilter(authenticationManager, jwtService);
    }

    private LogoutSuccessHandler getLogoutSuccessHandler() {
        return (((request, response, authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().flush();
        }));
    }

    private CorsConfigurationSource configurationSource() {

        return request -> {
            CorsConfiguration configuration = new CorsConfiguration();

            configuration.setAllowedHeaders(Collections.singletonList("*"));
            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
            configuration.setAllowCredentials(true);
            configuration.setExposedHeaders(List.of("atk"));

            return configuration;
        };
    }

    private AuthenticationEntryPoint getAuthenticationEntryPoint() {
        // 인증 X API 접근 (401 authorization)
        return (request, response, authentication) -> {
            CustomExceptionHandler.writeSecurityExceptionResponse(response, USER_UNAUTHORIZED);
        };
    }

    private AccessDeniedHandler getAccessDeniedHandler() {
        // 인증 O 인가 X API 접근 (403 forbidden)
        return (request, response, authentication) -> {
            CustomExceptionHandler.writeSecurityExceptionResponse(response, USER_FORBIDDEN);
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(Collections.singletonList(daoAuthenticationProvider));
    }

    @Bean
    public JwtVerifyFilter jwtVerifyFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtService jwtService) {
        return new JwtVerifyFilter(authenticationManager, userDetailsService, jwtService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
