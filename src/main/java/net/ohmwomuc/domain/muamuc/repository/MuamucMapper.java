package net.ohmwomuc.domain.muamuc.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Profile;

@Profile({ "!jpa" })
@Mapper
public interface MuamucMapper extends MuamucRepository {

}
