package net.ohmwomuc.core.security;

import jakarta.servlet.http.HttpServletResponse;
import net.ohmwomuc.core.exception.CustomExceptionHandler;
import net.ohmwomuc.core.security.filter.CustomEmailPasswordAuthenticationFilter;
import net.ohmwomuc.core.security.service.CustomRememberMeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
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
            "/swagger-ui/**",
            "/v3/api-docs/**"
    );

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           AuthenticationManager authenticationManager,
                                           SecurityContextRepository securityContextRepository,
                                           RememberMeServices rememberMeServices) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers(allowedRequestUrlList.toArray(String[]::new))
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(configurationSource()))
                .addFilterBefore(new CustomEmailPasswordAuthenticationFilter(
                        authenticationManager,
                        securityContextRepository,
                        rememberMeServices), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(ex -> ex.accessDeniedHandler(getAccessDeniedHandler())
                        .authenticationEntryPoint(getAuthenticationEntryPoint()))
                .rememberMe(r -> r.rememberMeServices(rememberMeServices))
                .logout(logout -> logout.logoutUrl("/api/security/logout")
                        .logoutSuccessHandler(getLogoutSuccessHandler())
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"));

        return http.build();
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
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RememberMeServices rememberMeServices(UserDetailsService userDetailsService, PersistentTokenRepository persistentTokenRepository) {
        return new CustomRememberMeService("uniqueAndSecretKey", userDetailsService, persistentTokenRepository);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);

        try {
            jdbcTokenRepository.removeUserTokens("test");
        } catch (Exception e) {
            jdbcTokenRepository.setCreateTableOnStartup(true);
        }

        return jdbcTokenRepository;
    }
}
