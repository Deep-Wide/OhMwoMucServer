package net.ohmwomuc.domain.report.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.ohmwomuc.domain.report.dto.Report;
import net.ohmwomuc.domain.report.dto.ReportTitle;
import net.ohmwomuc.domain.report.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
@Tag(name = "report", description = "report 컨트롤러")
public class ReportController {

    private final ReportService reportService;

    @PostMapping("")
    @Operation(summary = "Report 신고 추가")
    public ResponseEntity<Report.DomainResponse> addReport (@RequestBody Report.DomainRequest newReport) {
        Report.DomainResponse report =  reportService.addReport(newReport.toDomain()).toResponse();
        return ResponseEntity.ok(report);
    }

    @GetMapping("/title")
    @Operation(summary = "Report 제목 불러오기")
    public ResponseEntity<List<Map<String, Object>>> getReportTitleList() {
        return ResponseEntity.ok(Arrays.stream(ReportTitle.values()).map(ReportTitle::toMap).toList());
    }
}
