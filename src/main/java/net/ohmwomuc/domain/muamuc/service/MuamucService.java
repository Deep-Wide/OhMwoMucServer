package net.ohmwomuc.domain.muamuc.service;

import net.ohmwomuc.domain.muamuc.dto.Muamuc;

import java.util.List;

public interface MuamucService {
    List<Muamuc.Domain> getMuamucList(Muamuc.Condition condition);

    Muamuc.Domain findById(Muamuc.MuamucIdUserId ids);

    Muamuc.Domain addMuamucDomain(Muamuc.Domain muamuc);

    Muamuc.Domain updateMuamuc(Muamuc.DomainRequest muamuc);

    void deleteMuamuc(Integer id);
}
