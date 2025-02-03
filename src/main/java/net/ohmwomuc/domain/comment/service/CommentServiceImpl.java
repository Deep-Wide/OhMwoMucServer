package net.ohmwomuc.domain.comment.service;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.domain.comment.dto.Comment;
import net.ohmwomuc.domain.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment.Domain> getCommentListByMuamucId(int muamucId) {
        return commentRepository.getCommentListByMuamucId(muamucId);
    }

    @Override
    public Comment.Domain findCommentByCommentId(int commentId) {
        return commentRepository.findCommentByCommentId(commentId);
    }

    @Override
    @Transactional
    public Comment.Domain createComment(Comment.DomainRequest comment) {
        commentRepository.createComment(comment);
        return findCommentByCommentId(comment.getCommentId());
    }

    @Override
    @Transactional
    public Comment.Domain updateComment(Comment.DomainRequest comment) {
        commentRepository.updateComment(comment);
        return findCommentByCommentId(comment.getCommentId());
    }

    @Override
    public void deleteComment(int commentId) {
        commentRepository.deleteComment(commentId);
    }
}
