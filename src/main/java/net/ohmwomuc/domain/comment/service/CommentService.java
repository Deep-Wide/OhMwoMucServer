package net.ohmwomuc.domain.comment.service;

import net.ohmwomuc.domain.comment.dto.Comment;

import java.util.List;

public interface CommentService {
    List<Comment.Domain> getCommentListByMuamucId(int muamucId);

    Comment.Domain findCommentByCommentId(int commentId);

    Comment.Domain createComment(Comment.DomainRequest comment);

    Comment.Domain updateComment(Comment.DomainRequest comment);

    Comment.DomainResponse getCommentByCommentId(int commentId);

    void deleteComment(int commentId);

}
