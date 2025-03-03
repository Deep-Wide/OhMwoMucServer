package net.ohmwomuc.domain.report.dto;

import lombok.*;

import java.time.LocalDateTime;

public class Report {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Domain {

        int reportId;
        Integer muamucId;
        Integer commentId;
        int reporterUserId;
        int reportedUserId;
        ReportTitle reason;
        String detail;
        LocalDateTime createDt;
        LocalDateTime updateDt;

        public void setReason(int reasonId) {
            this.reason = ReportTitle.getById(reasonId).orElse(null);
        }

        public int getReasonId() {
            return reason.getId();
        }

        public DomainResponse toResponse() {
            return DomainResponse.builder()
                    .reportId(reportId)
                    .muamucId(muamucId != null ? muamucId : null)
                    .commentId(commentId != null ? commentId : null)
                    .reporterUserId(reporterUserId)
                    .reportedUserId(reportedUserId)
                    .reason(reason)
                    .detail(detail)
                    .createDt(createDt)
                    .updateDt(updateDt)
                    .build();
        }
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DomainResponse {
        int reportId;
        Integer muamucId;
        Integer commentId;
        int reporterUserId;
        int reportedUserId;
        ReportTitle reason;
        String detail;
        LocalDateTime createDt;
        LocalDateTime updateDt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DomainRequest {
        Integer muamucId;
        Integer commentId;
        int reporterUserId;
        int reportedUserId;
        ReportTitle reason;
        String detail;

        public void setReasonId(int reasonId) {
            this.reason = ReportTitle.getById(reasonId).orElse(null);
        }

        public void setReason(int reasonId) {
            this.reason = ReportTitle.getById(reasonId).orElse(null);
        }

        public int getReasonId() {
            return reason.getId();
        }

        public Domain toDomain() {
            return Domain.builder()
                    .muamucId(muamucId != null ? muamucId : null)
                    .commentId(commentId != null ? commentId : null)
                    .reporterUserId(reporterUserId)
                    .reportedUserId(reportedUserId)
                    .reason(reason)
                    .detail(detail)
                    .build();
        }
    }


}
