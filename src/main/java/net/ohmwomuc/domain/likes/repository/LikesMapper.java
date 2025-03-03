package net.ohmwomuc.domain.likes.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Profile;

@Profile("!jpa")
@Mapper
public interface LikesMapper extends LikesRepository {
}
