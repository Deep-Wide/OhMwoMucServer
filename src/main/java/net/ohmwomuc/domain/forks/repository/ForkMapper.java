package net.ohmwomuc.domain.forks.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Profile;

@Mapper
@Profile({"!jpa"})
public interface ForkMapper extends ForkRepository{
}
