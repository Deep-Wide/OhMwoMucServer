package net.ohmwomuc.domain.likes.dto;

import lombok.*;


public class Like {
    private int user_id;
    private int muamuc_id;
    private int likes_count;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DomainResponse {
        private Integer muamucId;
        private Integer likesCount;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DomainRequest {
        private Integer userId;
        private Integer muamucId;
    }
}
