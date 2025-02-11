package net.ohmwomuc.domain.likes.service;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.domain.likes.dto.Like;
import net.ohmwomuc.domain.likes.repository.LikesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService {

    private final LikesRepository likesRepository;

    @Override
    public Integer getLikesCountByMuamucId(Integer muamucId) {
        return likesRepository.getLikesCountByMuamucId(muamucId);
    }

    @Override
    @Transactional
    public Boolean reverseLike(Like.DomainRequest likeRequest) {
        if (Objects.isNull(likesRepository.getLikesByuserIdAndMuamucId(likeRequest))) {
            likesRepository.addLikesCount(likeRequest);
            return true;
        } else {
            likesRepository.deleteLikesCount(likeRequest);
            return false;
        }

    }

//    @Override
//    @Transactional
//    public Like.DomainResponse addLikesCountByMuamucId(Like.DomainRequest likeRequest) {
//        likesRepository.addLikesCount(likeRequest);
//        Like.DomainRequest output = Like.DomainRequest.builder()
//                .muamuc_id(likeRequest.getMuamuc_id())
//                .user_id(likeRequest.getUser_id())
//                .build();
//        return likesRepository.getLikesByuserIdAndMuamucId(output);
//    }
//
//    @Override
//    @Transactional
//    public void deleteLike(Like.DomainRequest likeRequest) {
//        likesRepository.deleteLikesCount(likeRequest);
//    }
}
