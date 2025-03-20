package net.ohmwomuc.domain.forks.service;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.domain.forks.dto.Fork;
import net.ohmwomuc.domain.forks.repository.ForkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ForkServiceImpl implements ForkService {

    private final ForkRepository forkRepository;

    @Override
    @Transactional
    public Boolean reverseFork(Fork fork) {
        if (Objects.isNull(forkRepository.getFork(fork))) {
            forkRepository.addFork(fork);
            return true;
        }
        forkRepository.deleteFork(fork);
        return false;
    }
}
