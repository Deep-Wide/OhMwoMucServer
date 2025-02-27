package net.ohmwomuc.domain.report.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum ReportTitle {
    SPAM(1, "스팸/홍보글"),
    PORNOGRAPHY(2, "음란물"),
    ILLEGAL_INFORMATION(3, "불법정보"),
    ABUSIVE_LANGUAGE(4, "욕설/혐오감 등의 표현"),
    PERSONAL_INFORMATION(5, "개인정보 노출"),
    DUPLICATE_POST(6, "도배글"),
    ETC(7, "기타");

    private final int id;
    private final String title;

    public static Optional<ReportTitle> getById(int reasonId) {
        return Arrays.stream(ReportTitle.values())
                .filter(reportTitle -> reportTitle.getId() == reasonId)
                .findAny();
    }

    public Map<String, Object> toMap() {
        Map<String, Object> reportTitleObj = new HashMap<>();

        reportTitleObj.put("id", id);
        reportTitleObj.put("title", title);

        return reportTitleObj;
    }
}
