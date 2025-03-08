package net.ohmwomuc.domain.restaurant.service;

import net.ohmwomuc.domain.restaurant.dto.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant.Domain createRestaurant(Restaurant.Domain domain);

    Restaurant.Domain findRestaurantInfo(Integer restaurantId);

    List<Restaurant.Domain> getRestaurantList(Restaurant.Condition condition);

    List<Restaurant.File> getRestaurantMenuImages(Integer restaurantId);

    void addRestaurantMenuImages(List<Restaurant.File> images, Integer restaurantId);
}
