package net.ohmwomuc.core.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.ohmwomuc.core.exception.CustomException;
import net.ohmwomuc.core.exception.CustomExceptionCode;
import net.ohmwomuc.core.exception.CustomExceptionHandler;
import net.ohmwomuc.core.security.authentication.CustomAuthenticationToken;
import net.ohmwomuc.core.security.dto.User;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static net.ohmwomuc.core.exception.CustomExceptionCode.FAILURE_AUTHENTICATION;

public class CustomEmailPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomEmailPasswordAuthenticationFilter(AuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository, RememberMeServices rememberMeServices) {
        super(authenticationManager);
        setFilterProcessesUrl("/api/security/login");
        setAuthenticationSuccessHandler(getAuthenticationSuccessHandler());
        setAuthenticationFailureHandler(getAuthenticationFailureHandler());
        setSecurityContextRepository(securityContextRepository);
        setRememberMeServices(rememberMeServices);
    }

    private AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(new Gson().toJson(authentication.getPrincipal()));
        };
    }

    private AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return (request, response, authentication) -> {
            CustomExceptionHandler.writeSecurityExceptionResponse(response, FAILURE_AUTHENTICATION);
        };

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = request.getInputStream()) {
            User.LoginRequest loginRequest = objectMapper.readValue(inputStream, User.LoginRequest.class);
            request.setAttribute("remember_me", loginRequest.isRememberMe());
            return this.getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (IOException e) {
            throw new CustomException(CustomExceptionCode.NOT_SUPPORTED_CONTENT_TYPE);
        }

    }
}
