package net.ohmwomuc.domain.taste.service;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.domain.taste.dto.Taste;
import net.ohmwomuc.domain.taste.repository.TasteReporitory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TasteServiceImpl implements TasteService {

    private final TasteReporitory tasteReporitory;

    @Override
    public void addTaste(Taste.Domain taste) {
        if (Objects.nonNull(tasteReporitory.getTasteInfo(taste))) {
            tasteReporitory.deleteTasteByRestaurantId(taste.getRestaurantId());
        }
        tasteReporitory.addTaste(taste);
    }
}
