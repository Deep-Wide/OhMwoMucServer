package net.ohmwomuc.core.security.oauth2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.ohmwomuc.core.exception.CustomException;
import net.ohmwomuc.core.exception.CustomExceptionCode;
import net.ohmwomuc.core.security.dto.User;
import net.ohmwomuc.core.security.service.JwtService;
import net.ohmwomuc.core.security.service.SecurityService;
import net.ohmwomuc.domain.user.dto.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static net.ohmwomuc.core.security.constant.UserRole.USER;
import static net.ohmwomuc.core.security.service.JwtService.BEARER;

public class CustomOauth2SuccessHandler implements AuthenticationSuccessHandler {

    private final SecurityService securityService;
    private final JwtService jwtService;

    public CustomOauth2SuccessHandler(SecurityService securityService, JwtService jwtService) {
        this.securityService = securityService;
        this.jwtService = jwtService;
    }

    @Value("${oauth2.client-host}")
    private String clientHost;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String registrationId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();

        User.UserAccountInfo user;

        if ("google".equals(registrationId)) {
            user = securityService.loadUserByGoogleKey(oAuth2User.getAttribute("sub"));

            if (Objects.isNull(user)) {
                user = securityService.createUser(User.UserAccountInfo.builder()
                        .googleKey(oAuth2User.getAttribute("sub"))
                        .email(oAuth2User.getAttribute("email"))
                        .nickname(oAuth2User.getAttribute("name"))
                        .role(Set.of(new SimpleGrantedAuthority(USER.toString())))
                        .build());
            }
        } else if ("kakao".equals(registrationId)) {
            String kakaoKey = ((Long) oAuth2User.getAttribute("id")).toString();
            user = securityService.loadUserByKakaoKey(kakaoKey);

            if (Objects.isNull(user)) {
                user = securityService.createUser(User.UserAccountInfo.builder()
                        .kakaoKey(kakaoKey)
                        .nickname(Optional.ofNullable(oAuth2User.getAttribute("kakao_account"))
                                .map(r -> ((LinkedHashMap) r).get("profile"))
                                .map(r -> (String) ((LinkedHashMap) r).get("nickname"))
                                .orElse(null))
                        .role(Set.of(new SimpleGrantedAuthority(USER.toString())))
                        .build());
                if (Objects.isNull(user)) {
                    throw new CustomException(CustomExceptionCode.FAILURE_AUTHENTICATION);
                }
            }

        } else {
            response.sendRedirect(new StringBuilder(clientHost).append("/login").toString());
            return;
        }
        if (user.getNickname() == null) {
            response.sendRedirect(new StringBuilder(clientHost).append("/signup/info").append("?id=").append(user.getId()).toString());
            return;
        }

        response.setHeader("atk", BEARER + jwtService.createAccessToken(authentication));

        Cookie cookie = new Cookie("rtk", jwtService.createRefreshToken(authentication));
        cookie.setMaxAge((int) jwtService.getRtkExpiredTime());
        cookie.setPath("/");
        response.addCookie(cookie);
        response.sendRedirect(String.format("%s/mypage?atk=%s", clientHost, BEARER + jwtService.createRefreshToken(authentication)));
    }
}
