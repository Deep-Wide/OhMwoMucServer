package net.ohmwomuc.core.security.service;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.core.security.dto.User;
import net.ohmwomuc.core.security.repository.SecurityRepository;
import net.ohmwomuc.domain.user.dto.UserInfo;
import net.ohmwomuc.domain.user.repository.UserRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService, UserDetailsService {

    private final SecurityRepository securityRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<User.UserAccountInfo> getLoginUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(authentication -> authentication.getPrincipal() instanceof User.UserAccountInfo)
                .map(authentication -> (User.UserAccountInfo) authentication.getPrincipal());
    }

    @Override
    public User.UserAccountInfo createUser(User.UserAccountInfo userInfo) {
        securityRepository.createUser(userInfo);
        UserInfo.Domain newUserInfo = userRepository.getUserInfo(userInfo.getId());

        User.UserAccountInfo newUser = User.UserAccountInfo
                .builder()
                .id(newUserInfo.getId())
                .email(newUserInfo.getEmail())
                .nickname(newUserInfo.getNickname())
                .googleKey(newUserInfo.getGoogleKey())
                .kakaoKey(newUserInfo.getKakaoKey())
                .role(newUserInfo.getRole())
                .build();
        return newUser;
    }

    @Override
    public User.UserAccountInfo loadUserByGoogleKey(String sub) {
        return securityRepository.findUserByGoogleKey(sub);
    }

    @Override
    public User.UserAccountInfo loadUserByKakaoKey(String kakaoId) {
        return securityRepository.findUserByKakaoKey(kakaoId);
    }

    @Override
    public User.UserAccountInfo loadUserByUsername(String username) throws UsernameNotFoundException {
        return securityRepository.findUserByUsername(username);
    }
}
