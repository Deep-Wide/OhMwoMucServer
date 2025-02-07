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
        private Integer muamuc_id;
        private Integer likes_count;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DomainRequest {
        private Integer user_id;
        private Integer muamuc_id;
    }
}
