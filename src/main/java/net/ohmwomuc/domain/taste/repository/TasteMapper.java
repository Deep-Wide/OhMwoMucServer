package net.ohmwomuc.domain.taste.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Profile;

@Mapper
@Profile({"!jpa"})
public interface TasteMapper extends TasteReporitory {
}
