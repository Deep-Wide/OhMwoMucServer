package net.ohmwomuc.domain.restaurant.service;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.core.security.service.SecurityService;
import net.ohmwomuc.domain.restaurant.dto.Restaurant;
import net.ohmwomuc.domain.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant.Domain> getRestaurantList(Restaurant.Condition condition) {
        return restaurantRepository.getRestaurantList(condition);
    }

    @Override
    @Transactional
    public Restaurant.Domain createRestaurant(Restaurant.Domain restaurantInfo) {

        restaurantRepository.createRestaurantInfo(restaurantInfo);

        restaurantInfo.getOpenTimeList().forEach(openTime -> {
            openTime.setRestaurantId(restaurantInfo.getRestaurantId());
            openTime.setOpenTimeType(restaurantInfo.getOpenTimeType());
        });

        restaurantInfo.getMenuList().forEach(menu -> {
            menu.setRestaurantId(restaurantInfo.getRestaurantId());
        });

        restaurantInfo.getMenuImageList().forEach(menuImage -> {
            menuImage.setRestaurantId(restaurantInfo.getRestaurantId());
        });

        if (!restaurantInfo.getOpenTimeList().isEmpty()) {
            restaurantRepository.addRestaurantOpenTime(restaurantInfo.getOpenTimeList());
        }
        if (!restaurantInfo.getMenuList().isEmpty()) {
            restaurantRepository.addRestaurantMenu(restaurantInfo.getMenuList());
        }
        if (!restaurantInfo.getMenuImageList().isEmpty()) {
            restaurantRepository.addRestaurantImages(restaurantInfo.getMenuImageList());
        }

        return restaurantRepository.getRestaurantInfo(restaurantInfo.getRestaurantId());
    }

    @Override
    public Restaurant.Domain findRestaurantInfo(Integer restaurantId) {
        return restaurantRepository.getRestaurantInfo(restaurantId);
    }


    @Override
    public List<Restaurant.File> getRestaurantMenuImages(Integer restaurantId) {
        return restaurantRepository.getRestaurantMenuImages(restaurantId);
    }

    @Override
    public void addRestaurantMenuImages(List<Restaurant.File> images, Integer restaurantId) {
        if (images.size() > 0) {
            restaurantRepository.addRestaurantImages(images);
        }
    }

}
