package net.ohmwomuc.domain.muamuc.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum Tag {
    TODAY_LUNCH(1, "오점먹"),
    TODAY_DINNER(2, "오저먹"),
    WHAT_TODAY_LUNCH(3, "오점뭐"),
    WHAT_TODAY_DINNER(4, "오저뭐"),
    DIET(5, "다이어트"),
    RECOMMEND_RESTAURANT(6, "식당추천"),
    EATING_ALONE(7, "혼밥"),
    DRINKING_ALONE(8, "혼술");

    private final Integer id;
    private final String name;

    public static Optional<Tag> getById(int id) {
        return Arrays.stream(Tag.values())
                .filter(tag -> tag.getId() == id)
                .findAny();
    }
}
