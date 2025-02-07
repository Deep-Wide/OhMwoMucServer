package net.ohmwomuc.domain.likes.repository;

import net.ohmwomuc.domain.likes.dto.Like;

public interface LikesRepository {
    Integer getLikesCountByMuamucId(int muamucId);
    void addLikesCount(Like.DomainRequest likeRequest);
    Like.DomainResponse getLikesByuserIdAndMuamucId(Like.DomainRequest likeRequest);
    void deleteLikesCount(Like.DomainRequest likeRequest);
}
