package net.ohmwomuc.core.security.service;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.core.security.dto.User;
import net.ohmwomuc.core.security.repository.SecurityRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SercurityServiceImpl implements SecurityService, UserDetailsService {

    private final SecurityRepository securityRepository;

    @Override
    public Optional<User.UserAccountInfo> getLoginUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(authentication -> authentication.getPrincipal() instanceof User.UserAccountInfo)
                .map(authentication -> (User.UserAccountInfo) authentication.getPrincipal());
    }

    @Override
    public User.UserAccountInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        return securityRepository.findUserByEmail(username);
    }
}
