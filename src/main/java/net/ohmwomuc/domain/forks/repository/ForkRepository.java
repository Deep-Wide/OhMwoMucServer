package net.ohmwomuc.domain.forks.repository;

import net.ohmwomuc.domain.forks.dto.Fork;

public interface ForkRepository {

    Fork getFork(Fork fork);

    void deleteFork(Fork fork);

    Boolean addFork(Fork fork);
}
