package net.ohmwomuc.domain.restaurant.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Profile;

@Profile({ "!jpa" })
@Mapper
public interface RestaurantMapper extends RestaurantRepository {
}
