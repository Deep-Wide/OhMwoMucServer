package net.ohmwomuc.domain.muamuc.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Getter
public enum Tag {
    TODAY_LUNCH(0, "오점먹"),
    TODAY_DINNER(1, "오저먹"),
    WHAT_TODAY_LUNCH(2, "오점뭐"),
    WHAT_TODAY_DINNER(3, "오저뭐"),
    DIET(4, "다이어트"),
    RECOMMEND_RESTAURANT(5, "식당추천"),
    EATING_ALONE(6, "혼밥"),
    DRINKING_ALONE(7, "혼술");

    private final Integer id;
    private final String name;

    public static Optional<Tag> getById(int id) {
        return Arrays.stream(Tag.values())
                .filter(tag -> tag.getId() == id)
                .findAny();
    }
}
