package net.ohmwomuc.domain.likes.service;

import net.ohmwomuc.domain.likes.dto.Like;

public interface LikesService {

    Integer getLikesCountByMuamucId(Integer muamucId);
    Boolean reverseLike(Like.DomainRequest likeRequest);
}
