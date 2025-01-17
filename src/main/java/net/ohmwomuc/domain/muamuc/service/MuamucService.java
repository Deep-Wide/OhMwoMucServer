package net.ohmwomuc.domain.muamuc.service;

import net.ohmwomuc.domain.muamuc.dto.Muamuc;

import java.util.List;

public interface MuamucService {
    List<Muamuc.Domain> getMuamucList(Muamuc.Condition condition);

    Muamuc.Domain findById(int id);

    Muamuc.Domain addMuamucDomain(Muamuc.Domain muamuc);

    Muamuc.Domain updateMuamuc(Muamuc.Domain muamuc);

    void deleteMuamucDomain(int id);
}
