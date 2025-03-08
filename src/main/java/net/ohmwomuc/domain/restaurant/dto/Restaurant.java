package net.ohmwomuc.domain.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class Restaurant {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Domain {
        private int restaurantId;
        private String name;
        private int categoryId;
        private double lat;
        private double lng;
        private List<OpenTime> openTimeList;
        private List<Menu> menuList;
        private List<File> menuImageList;
        private int openTimeType;
        private String tel;
        private String zipCode;
        private String address;
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
                    .menuImageList(menuImageList)
                    .openTimeType(openTimeType)
                    .address(address)
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
        private int restaurantId;
        private String name;
        private int categoryId;
        private double lat;
        private double lng;
        private String zipCode;
        private List<OpenTime> openTimeList;
        private List<Menu> menuList;
        private List<File> menuImageList;
        private int openTimeType;
        private String tel;
        private String address;
        private int writerId;
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
                    .menuImageList(menuImageList)
                    .openTimeType(openTimeType)
                    .tel(tel)
                    .zipCode(zipCode)
                    .address(address)
                    .writerId(writerId)
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
        private int restaurantId;
        private String name;
        private int categoryId;
        private double lat;
        private double lng;
        private List<OpenTime> openTimeList;
        private List<Menu> menuList;
        private List<File> menuImageList;
        private String address;
        private int openTimeType;
        private String tel;

        public Domain toDomain() {
            return Domain.builder()
                    .restaurantId(restaurantId)
                    .name(name)
                    .categoryId(categoryId)
                    .lat(lat)
                    .lng(lng)
                    .openTimeList(openTimeList)
                    .openTimeType(openTimeType)
                    .menuImageList(menuImageList)
                    .address(address)
                    .tel(tel)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class OpenTime {
        private int restaurantId;
        private int openTimeType;
        private int day;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        private LocalTime startTime;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
        private LocalTime endTime;
    }

    @Getter
    @Setter
    public static class Menu {
        private int restaurantId;
        private int seq;
        private String name;
        private int price;
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
