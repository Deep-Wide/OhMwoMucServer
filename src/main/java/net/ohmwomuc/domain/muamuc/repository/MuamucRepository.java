package net.ohmwomuc.domain.muamuc.repository;

import net.ohmwomuc.domain.muamuc.dto.Muamuc;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface MuamucRepository {

    List<Muamuc.Domain> findAllDomain(Muamuc.Condition condition);

    Muamuc.Domain findDomainById(int id);

    void createMuamuc(Muamuc.Domain muamuc);

    void updateMuamuc(Muamuc.Domain muamuc);

    void deleteMuamuc(int id);

}
