package net.ohmwomuc.core.security.authentication;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.core.security.dto.User;
import net.ohmwomuc.core.security.service.SecurityService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;

    private Map<String, User.UserAccountInfo> userDB = new ConcurrentHashMap<>();

    public CustomAuthenticationProvider(PasswordEncoder passwordEncoder, SecurityService securityService) {
        this.passwordEncoder = passwordEncoder;
        this.securityService = securityService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;

        User.UserAccountInfo account = securityService.loadUserByUserName(token.getName());

        if (account != null
                && passwordEncoder.matches(token.getCredentials(), account.getPassword())) {
            //인증 성공
            return CustomAuthenticationToken.builder()
                    .principal(User.Principal.builder()
                            .id(account.getId())
                            .email(account.getEmail())
                            .nickname(account.getNickname())
                            .role(account.getRole())
                            .build()
                    )
                    .credentials(null)
                    .details(token.getDetails())
                    .authenticated(true)
                    .build();
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.equals(authentication);
    }
}
