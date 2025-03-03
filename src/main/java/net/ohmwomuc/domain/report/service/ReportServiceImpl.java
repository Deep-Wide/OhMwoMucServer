package net.ohmwomuc.domain.report.service;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.domain.report.dto.Report;
import net.ohmwomuc.domain.report.repository.ReportRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public Report.Domain addReport(Report.Domain domain) {
        reportRepository.addReport(domain);
        return reportRepository.findReport(domain.getReportId());
    }
}
