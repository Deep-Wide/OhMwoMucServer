package net.ohmwomuc.domain.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Profile;

@Profile({"!jpa"})
@Mapper
public interface UserMapper extends UserRepository  {
}
