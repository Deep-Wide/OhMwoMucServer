package net.ohmwomuc.domain.user.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

public class UserInfo {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Domain {
        private int id;
        private String nickname;
        private String email;
        private String naverKey;
        private String kakaoKey;
        private String googleKey;
        private String password;
        private Set<GrantedAuthority> role;

        public Response toResponse() {
            return Response.builder()
                    .id(id)
                    .nickname(nickname)
                    .email(email)
                    .naverKey(naverKey)
                    .kakaoKey(kakaoKey)
                    .googleKey(googleKey)
                    .role(role)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private int id;
        private String nickname;
        private String email;
        private String naverKey;
        private String kakaoKey;
        private String googleKey;
        private String password;
        private Set<GrantedAuthority> role;

        public Domain toDomain() {
            return Domain.builder()
                    .id(id)
                    .nickname(nickname)
                    .email(email)
                    .naverKey(naverKey)
                    .kakaoKey(kakaoKey)
                    .googleKey(googleKey)
                    .password(password)
                    .role(role)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private int id;
        private String nickname;
        private String email;
        private String naverKey;
        private String kakaoKey;
        private String googleKey;
        private Set<GrantedAuthority> role;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Image {
        private int id;
        private int fileId;
        private String uniqueFileName;
        private String fileName;

    }

}
