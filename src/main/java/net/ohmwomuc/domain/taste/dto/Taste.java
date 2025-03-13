package net.ohmwomuc.domain.taste.dto;

import lombok.*;

public class Taste {
    public int userId;
    public int restaurantId;
    public int tasteCode;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Domain {
        public int userId;
        public int restaurantId;
        public int tasteCode;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        public int restaurantId;
        public int tasteCode;

        public Domain toDomain() {
            return Domain.builder()
                    .restaurantId(restaurantId)
                    .tasteCode(tasteCode)
                    .build();
        }
    }
}
