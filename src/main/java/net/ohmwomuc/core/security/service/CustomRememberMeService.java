package net.ohmwomuc.core.security.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public class CustomRememberMeService extends PersistentTokenBasedRememberMeServices {

    private final String REMEMBER_ME_KEY = "remember_me";

    public CustomRememberMeService(String key, UserDetailsService userDetailsService, PersistentTokenRepository tokenRepository) {
        super(key, userDetailsService, tokenRepository);
        setParameter(REMEMBER_ME_KEY);
    }

    @Override
    protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {
       Object rememberMe = request.getAttribute(REMEMBER_ME_KEY);
       return rememberMe != null && Boolean.TRUE.equals(rememberMe);
    }
}
