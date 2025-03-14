package net.ohmwomuc.domain.taste.repository;

import net.ohmwomuc.domain.taste.dto.Taste;

public interface TasteReporitory {
    Taste.Domain getTasteInfo(Taste.Domain taste);

    void deleteTaste(Taste.Domain taste);

    void addTaste(Taste.Domain taste);
}
