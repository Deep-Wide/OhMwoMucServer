package net.ohmwomuc.domain.restaurant.repository;

import net.ohmwomuc.domain.restaurant.dto.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    List<Restaurant.Domain> getRestaurantList(Restaurant.Condition condition);

    void createRestaurantInfo(Restaurant.Domain restaurantInfo);

    void addRestaurantOpenTime(List<Restaurant.OpenTime> openTimeList);

    void addRestaurantMenu(List<Restaurant.Menu> menuList);

    Restaurant.Domain getRestaurantInfo(int restaurantId);

    List<Restaurant.File> getRestaurantMenuImages(Integer restaurantId);

    void addRestaurantImages(List<Restaurant.File> images);
}
