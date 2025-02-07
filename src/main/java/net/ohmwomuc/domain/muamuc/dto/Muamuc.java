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
        private MuamucTag muamucTag;
        private String title;
        private String content;
        private int restaurantId;
        private int writerId;
        private String writerName;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime deletedAt;
        private boolean isDel;
        private int commentCount;
        private int likeCount;

        public void setTagId(int tagId) {
            this.muamucTag = MuamucTag.getById(tagId).get();
        }

        public int getTagId() {
            return muamucTag.getId();
        }

        public DomainResponse toResponse() {
            return DomainResponse.builder()
                    .muamucId(muamucId)
                    .tagId(muamucTag.getId())
                    .title(title)
                    .content(content)
                    .restaurantId(restaurantId)
                    .writerId(writerId)
                    .writerName(writerName)
                    .createdAt(createdAt)
                    .updatedAt(updatedAt)
                    .commentCount(commentCount)
                    .likeCount(likeCount)
                    .build();

        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Muamuc response")
    public static class DomainResponse {
        @Schema(description = "뭐먹 ID")
        private int muamucId;
        @Schema(description = "태그 ID")
        private int tagId;
        @Schema(description = "제목")
        private String title;
        @Schema(description = "내용")
        private String content;
        @Schema(description = "식당 ID")
        private int restaurantId;
        @Schema(description = "작성자 ID")
        private int writerId;
        @Schema(description = "작성자 닉네임")
        private String writerName;
        @Schema(description = "생성 일시")
        private LocalDateTime createdAt;
        @Schema(description = "수정 일시")
        private LocalDateTime updatedAt;
        @Schema(description = "댓글 개수")
        private int commentCount;
        @Schema(description = "좋아요 개수")
        private int likeCount;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "Muamuc Request")
    public static class DomainRequest {
        @Schema(description = "뭐먹 ID")
        private int muamucId;
        @Schema(description = "태그")
        private MuamucTag muamucTag;
        @Schema(description = "제목")
        private String title;
        @Schema(description = "내용")
        private String content;
        @Schema(description = "식당 ID")
        private int restaurantId;
        @Schema(description = "작성자 ID")
        private int writerId;
        @Schema(description = "작성자 닉네임")
        private String writerName;

        public void setTagId(int tagId) {
            this.muamucTag = MuamucTag.getById(tagId).get();
        }

        public int getTagId() {
            return muamucTag.getId();
        }

        public Domain toDomain() {
            return Muamuc.Domain.builder()
                    .muamucId(muamucId)
                    .muamucTag(muamucTag)
                    .title(title)
                    .content(content)
                    .restaurantId(restaurantId)
                    .writerId(writerId)
                    .writerName(writerName)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Condition {
        private MuamucTag muamucTag;
        private String searchKeyword;

        public int getId() {
            return this.muamucTag.getId();
        }

    }
}
