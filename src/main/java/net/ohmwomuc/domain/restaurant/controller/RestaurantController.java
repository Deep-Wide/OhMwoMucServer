package net.ohmwomuc.domain.restaurant.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.ohmwomuc.core.security.service.SecurityService;
import net.ohmwomuc.domain.restaurant.dto.Restaurant;
import net.ohmwomuc.domain.restaurant.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/api/restaurant")
@RestController
@RequiredArgsConstructor
@Tag(name = "Restaurant", description = "Restaurant컨트롤러")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final SecurityService securityService;

    @GetMapping("")
    @Operation(summary = "Restaurant 목록 조회")
    public ResponseEntity<List<Restaurant.DomainResponse>> getRestaurantList(@RequestParam(name = "searchKeyword", required = false) String searchKeyword) {
        Restaurant.Condition condition = Restaurant.Condition.builder()
                .searchKeyword(searchKeyword)
                .build();

        List<Restaurant.Domain> restaurantList = restaurantService.getRestaurantList(condition);

        return ResponseEntity.ok(restaurantList.stream().map(Restaurant.Domain::toResponse).toList());
    }


    @PostMapping("")
    @Operation(summary = "Restaurant 추가")
    public ResponseEntity<Restaurant.DomainResponse> createRestaurant(@RequestBody Restaurant.DomainRequest restaurantInfo) {
        restaurantInfo.setWriterId(securityService.getLoginUser().get().getId());
        Restaurant.DomainResponse response = restaurantService.createRestaurant(restaurantInfo.toDomain()).toResponse();

        return ResponseEntity.status(CREATED).body(response);
    }

    @GetMapping("/{restaurantId}")
    @Operation(summary = "Restaurant 정보 조회")
    public ResponseEntity<Restaurant.DomainResponse> getRestaurantInfo(@PathVariable("restaurantId") Integer restaurantId) {
        Restaurant.Domain restaurant = restaurantService.findRestaurantInfo(restaurantId);

        return ResponseEntity.ok(restaurant.toResponse());
    }

    @GetMapping("{restaurantId}/images")
    @Operation(summary = "restaurant 메뉴판 사진 조회")
    public ResponseEntity<List<Restaurant.File>> getMenuImages(@PathVariable("restaurantId") Integer restaurantId) {
        return ResponseEntity.ok(restaurantService.getRestaurantMenuImages(restaurantId));
    }

    @PostMapping("{restaurantId}/images")
    @Operation(summary = "restaurant 메뉴판 사진 저장")
    public ResponseEntity<Void> addMuamucFile(@RequestBody List<Restaurant.File> images, @PathVariable Integer restaurantId) {
        restaurantService.addRestaurantMenuImages(images, restaurantId);
        return ResponseEntity.ok().build();
    }
}
