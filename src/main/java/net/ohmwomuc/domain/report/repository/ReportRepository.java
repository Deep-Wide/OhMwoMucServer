package net.ohmwomuc.domain.report.repository;

import net.ohmwomuc.domain.report.dto.Report;

public interface ReportRepository {

    void addReport(Report.Domain domain);

    Report.Domain findReport(int reportId);
}
