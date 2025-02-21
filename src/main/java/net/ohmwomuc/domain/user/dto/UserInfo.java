package net.ohmwomuc.domain.user.dto;

import lombok.*;

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

        public Response toResponse() {
            return Response.builder()
                    .id(id)
                    .nickname(nickname)
                    .email(email)
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
        private String password;

        public Domain toDomain() {
            return Domain.builder()
                    .id(id)
                    .nickname(nickname)
                    .email(email)
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
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Image {
        private int userId;
        private int fileId;
        private String uniqueFileName;
        private String fileName;

    }

}
