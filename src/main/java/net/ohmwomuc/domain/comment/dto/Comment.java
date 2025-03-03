package net.ohmwomuc.domain.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

public class Comment {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Domain {
        private int commentId;
        private int userId;
        private String writerName;
        private int parentId;
        private int muamucId;
        private String commentText;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime deletedAt;
        private boolean isDel;

        public DomainResponse toResponse() {
            return DomainResponse.builder()
                    .commentId(commentId)
                    .userId(userId)
                    .writerName(writerName)
                    .parentId(parentId)
                    .muamucId(muamucId)
                    .commentText(commentText)
                    .updatedAt(updatedAt)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Comment 도메인 Response")
    public static class DomainResponse {
        @Schema(description = "댓글 ID")
        private int commentId;
        @Schema(description = "작성자 ID")
        private int userId;
        @Schema(description = "작성자 닉네임")
        private String writerName;
        @Schema(description = "부모 ID")
        private int parentId;
        @Schema(description = "게시글 ID")
        private int muamucId;
        @Schema(description = "댓글 내용")
        private String commentText;
        @Schema(description = "댓글 수정일자")
        private LocalDateTime updatedAt;

        public Domain toDomain() {
            return Comment.Domain.builder()
                    .commentId(commentId)
                    .userId(userId)
                    .writerName(writerName)
                    .parentId(parentId)
                    .muamucId(muamucId)
                    .commentText(commentText)
                    .updatedAt(updatedAt)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Schema(description = "Comment 도메인 Request")
    public static class DomainRequest {
        @Schema(description = "댓글 ID")
        private int commentId;
        @Schema(description = "작성자 ID")
        private int userId;
        @Schema(description = "부모 ID")
        private int parentId;
        @Schema(description = "게시글 ID")
        private int muamucId;
        @Schema(description = "댓글 내용")
        private String commentText;

        public Domain toDomain() {
            return Comment.Domain.builder()
                    .commentId(commentId)
                    .userId(userId)
                    .parentId(parentId)
                    .muamucId(muamucId)
                    .commentText(commentText)
                    .build();
        }
    }


}
