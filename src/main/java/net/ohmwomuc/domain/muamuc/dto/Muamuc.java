package net.ohmwomuc.domain.muamuc.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

public class Muamuc {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Domain {

        private int muamucId;
        private Tag tag;
        private String title;
        private String content;
        private int restaurantId;
        private int writerId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime deletedAt;
        private boolean isDel;

        public void setTagId(int tagId) {
            this.tag = Tag.getById(tagId).get();
        }

        public int getTagId() {
            return tag.getId();
        }

        public DomainResponse toResponse() {
            return DomainResponse.builder()
                    .muamuc_id(muamucId)
                    .tag(tag)
                    .title(title)
                    .content(content)
                    .restaurant_id(restaurantId)
                    .writer_id(writerId)
                    .created_dt(createdAt)
                    .update_dt(updatedAt)
                    .build();

        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Muamuc 도메인 request")
    public static class DomainResponse {
        @Schema(description = "뭐먹 ID")
        private int muamuc_id;
        @Schema(description = "태그")
        private Tag tag;
        @Schema(description = "제목")
        private String title;
        @Schema(description = "내용")
        private String content;
        @Schema(description = "식당 ID")
        private int restaurant_id;
        @Schema(description = "작성자 ID")
        private int writer_id;
        @Schema(description = "생성 일시")
        private LocalDateTime created_dt;
        @Schema(description = "수정 일시")
        private LocalDateTime update_dt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Muamuc Request")
    public static class DomainRequest {
        @Schema(description = "뭐먹 ID")
        private int muamuc_id;
        @Schema(description = "태그")
        private Tag tag;
        @Schema(description = "제목")
        private String title;
        @Schema(description = "내용")
        private String content;
        @Schema(description = "식당 ID")
        private int restaurant_id;
        @Schema(description = "작성자 ID")
        private int writer_id;

        public void setTagId(int tagId) {
            this.tag = Tag.getById(tagId).get();
        }

        public Domain toDomain() {
            return Muamuc.Domain.builder()
                    .muamucId(muamuc_id)
                    .tag(tag)
                    .title(title)
                    .content(content)
                    .restaurantId(restaurant_id)
                    .writerId(writer_id)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Condition {
        private Tag tag;
        private String searchKeyword;

        public int getId() {
            return this.tag.getId();
        }

    }
}
