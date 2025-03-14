package net.ohmwomuc.domain.taste.service;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.domain.taste.dto.Taste;
import net.ohmwomuc.domain.taste.repository.TasteReporitory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TasteServiceImpl implements TasteService {

    private final TasteReporitory tasteReporitory;

    @Override
    @Transactional
    public void addTaste(Taste.Domain taste) {
        if (Objects.nonNull(tasteReporitory.getTasteInfo(taste))) {
            tasteReporitory.deleteTaste(taste);
            if (taste.getTasteCode() == 0)
                return;
        }
        tasteReporitory.addTaste(taste);
    }
}
