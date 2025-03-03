package net.ohmwomuc.domain.report.service;

import net.ohmwomuc.domain.report.dto.Report;

public interface ReportService {
    Report.Domain addReport(Report.Domain domain);
}
