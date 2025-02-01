package net.ohmwomuc.core.security.service;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.core.security.dto.User;
import net.ohmwomuc.core.security.repository.SecurityRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SercurityServiceImpl implements SecurityService {

    private final SecurityRepository securityRepository;

    @Override
    public Optional<User.Principal> getLoginUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(authentication -> authentication.getPrincipal() instanceof User.Principal)
                .map(authentication -> (User.Principal) authentication.getPrincipal());
    }

    @Override
    public User.UserAccountInfo loadUserByUserName(String name) {
        return securityRepository.findUserByEmail(name);
    }
}
