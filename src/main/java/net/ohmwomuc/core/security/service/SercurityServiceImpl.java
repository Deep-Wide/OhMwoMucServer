package net.ohmwomuc.core.security.service;

import net.ohmwomuc.core.security.dto.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SercurityServiceImpl implements SecurityService {

    @Override
    public Optional<User.Principal> getLoginUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(authentication -> authentication.getPrincipal() instanceof User.Principal)
                .map(authentication -> (User.Principal) authentication.getPrincipal());
    }
}
