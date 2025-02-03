package net.ohmwomuc.domain.comment.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Profile;

@Profile({ "!jpa" })
@Mapper
public interface CommentMapper extends CommentRepository{
}
