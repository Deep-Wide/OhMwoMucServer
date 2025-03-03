package net.ohmwomuc.domain.report.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Profile;

@Profile({"!jpa"})
@Mapper
public interface ReportMapper extends ReportRepository {

}
