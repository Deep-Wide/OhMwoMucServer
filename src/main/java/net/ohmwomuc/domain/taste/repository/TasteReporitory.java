package net.ohmwomuc.domain.taste.repository;

import net.ohmwomuc.domain.taste.dto.Taste;

public interface TasteReporitory {
    Taste getTasteInfo(Taste.Domain taste);

    void deleteTasteByRestaurantId(int restaurantId);

    void addTaste(Taste.Domain taste);
}
