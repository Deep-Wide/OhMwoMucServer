package net.ohmwomuc.domain.restaurant.dto;

import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public class Restaurant {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Domain {
        int restaurantId;
        String name;
        int categoryId;
        double lat;
        double lng;
        List<OpenTime> openTimeList;
        List<Menu> menuList;
        int openTimeType;
        String tel;
        String zoneCode;
        int writerId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public DomainResponse toResponse() {
            return DomainResponse
                    .builder()
                    .restaurantId(restaurantId)
                    .name(name)
                    .categoryId(categoryId)
                    .lat(lat)
                    .lng(lng)
                    .openTimeList(openTimeList)
                    .menuList(menuList)
                    .openTimeType(openTimeType)
                    .tel(tel)
                    .build();

        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DomainRequest {
        int restaurantId;
        String name;
        int categoryId;
        double lat;
        double lng;
        List<OpenTime> openTimeList;
        List<Menu> menuList;
        int openTimeType;
        String tel;
        String zoneCode;
        int writerId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Domain toDomain() {
            return Domain.builder()
                    .restaurantId(restaurantId)
                    .name(name)
                    .categoryId(categoryId)
                    .lat(lat)
                    .lng(lng)
                    .openTimeList(openTimeList)
                    .menuList(menuList)
                    .openTimeType(openTimeType)
                    .tel(tel)
                    .writerId(writerId)
                    .zoneCode(zoneCode)
                    .createdAt(createdAt)
                    .updatedAt(updatedAt)
                    .build();
        }

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class DomainResponse {
        int restaurantId;
        String name;
        int categoryId;
        double lat;
        double lng;
        List<OpenTime> openTimeList;
        List<Menu> menuList;
        int openTimeType;
        String tel;

        public Domain toDomain() {
            return Domain.builder()
                    .restaurantId(restaurantId)
                    .name(name)
                    .categoryId(categoryId)
                    .lat(lat)
                    .lng(lng)
                    .openTimeList(openTimeList)
                    .openTimeType(openTimeType)
                    .tel(tel)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class OpenTime {
        int restaurantId;
        int openTimeType;
        int day;
        Time startTime;
        Time endTime;
    }

    @Getter
    @Setter
    public static class Menu {
        int restaurantId;
        String name;
        int price;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class File {
        private int restaurantId;
        private int fileId;
        private String uniqueFileName;
        private String fileName;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Condition {
        private double lat;
        private double lng;
        private String name;
    }
}
