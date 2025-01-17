package net.ohmwomuc.core.security.authentication;

import jakarta.annotation.PostConstruct;
import net.ohmwomuc.core.security.dto.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private Map<String, User.UserAccountInfo> userDB = new ConcurrentHashMap<>();


    @PostConstruct
    public void initUser() {
        userDB.put("popo@naver.com", User.UserAccountInfo.builder()
                .id(3)
                .email("popo@naver.com")
                .nickname("포포")
                .password("1q2w3e4r")
                .role(Set.of(new SimpleGrantedAuthority("ROLE_USER")))
                .build());

        userDB.put("qwqw@google.com", User.UserAccountInfo.builder()
                .id(7)
                .email("qwqw@google.com")
                .nickname("관리자")
                .password("qawsedrf")
                .role(Set.of(new SimpleGrantedAuthority("ROLE_ADMIN")))
                .build());
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;

        return Optional.ofNullable(userDB.get(token.getName()))
                .filter(account -> account.getPassword().equals(token.getCredentials())) // 인증 정보 확인 앖으면 Optional.empty() 반환
                .map(account -> CustomAuthenticationToken.builder()
                        .principal(User.Principal.builder()
                                .email(account.getEmail())
                                .nickname(account.getNickname())
                                .role(account.getRole())
                                .build()
                        )
                        .credentials(null)
                        .details(token.getDetails())
                        .authenticated(true)
                        .build()
                )
                .orElse(null); // 없을 경우 null 반환
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.equals(authentication);
    }
}
