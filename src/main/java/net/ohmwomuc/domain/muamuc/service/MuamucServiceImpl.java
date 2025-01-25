package net.ohmwomuc.domain.muamuc.service;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.domain.muamuc.dto.Muamuc;
import net.ohmwomuc.domain.muamuc.repository.MuamucRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MuamucServiceImpl implements MuamucService {

    private final MuamucRepository muamucRepository;

    @Override
    public List<Muamuc.Domain> getMuamucList(Muamuc.Condition condition) {
        return muamucRepository.findAllDomain(condition);
    }

    @Override
    public Muamuc.Domain findById(int id) {
        return muamucRepository.findDomainById(id);
    }

    @Override
    @Transactional
    public Muamuc.Domain addMuamucDomain(Muamuc.Domain muamuc) {
        if (StringUtils.isEmpty(muamuc.getTitle())) {
            throw new IllegalArgumentException("Muamuc title cannot be empty");
        }
        muamucRepository.createMuamuc(muamuc);
        return muamucRepository.findDomainById(muamuc.getMuamucId());
    }

    @Override
    @Transactional
    public Muamuc.Domain updateMuamuc(Muamuc.DomainRequest muamuc) {
        muamucRepository.updateMuamuc(muamuc);
        return muamucRepository.findDomainById(muamuc.getMuamucId());
    }

    @Override
    public void deleteMuamuc(Integer id) {
        muamucRepository.deleteMuamuc(id);
    }
}
