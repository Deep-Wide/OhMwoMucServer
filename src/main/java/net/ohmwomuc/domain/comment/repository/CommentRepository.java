package net.ohmwomuc.domain.comment.repository;

import net.ohmwomuc.domain.comment.dto.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment.Domain> getCommentListByMuamucId(int muamucId);

    Comment.Domain findCommentByCommentId(int commentId);

    void createComment(Comment.DomainRequest comment);

    void updateComment(Comment.DomainRequest comment);

    void deleteComment(int commentId);
}
