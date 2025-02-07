package net.ohmwomuc.domain.likes.service;

import net.ohmwomuc.domain.likes.dto.Like;

public interface LikesService {

    Integer getLikesCountByMuamucId(Integer muamucId);
    Like.DomainResponse addLikesCountByMuamucId(Like.DomainRequest likeRequest);
    void deleteLike(Like.DomainRequest likeRequest);
}
