package net.ohmwomuc.domain.comment.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.ohmwomuc.core.exception.CustomException;
import net.ohmwomuc.core.exception.CustomExceptionCode;
import net.ohmwomuc.core.security.service.SecurityService;
import net.ohmwomuc.domain.comment.dto.Comment;
import net.ohmwomuc.domain.comment.service.CommentService;
import net.ohmwomuc.domain.muamuc.dto.Muamuc;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequestMapping("api/comment")
@RestController
@RequiredArgsConstructor
@Tag(name = "Comment", description = "Comment컨트롤러")
public class CommentController {

    private final CommentService commentService;
    private final SecurityService securityService;

    @GetMapping("/{muamucId}")
    public ResponseEntity<List<Comment.DomainResponse>> getCommentList(@PathVariable("muamucId") int muamucId) {
        List<Comment.Domain> commentList = commentService.getCommentListByMuamucId(muamucId);

        return ResponseEntity.ok(commentList.stream()
                .map(Comment.Domain::toResponse)
                .collect(Collectors.toList()));
    }

    @PostMapping("")
    public ResponseEntity<Comment.DomainResponse> addComment(@RequestBody Comment.DomainRequest comment) {
//        securityService.getLoginUser().orElseThrow(() -> new CustomException(CustomExceptionCode.USER_UNAUTHORIZED));
        Comment.DomainResponse response = commentService.createComment(comment).toResponse();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{commentId}")
    public ResponseEntity<Comment.DomainResponse> updataCommentById(@PathVariable("commentId") int commentId, @RequestBody Comment.DomainRequest comment) {
        if (commentId != comment.getCommentId()) {
            throw new CustomException(CustomExceptionCode.NOT_SUPPORTED_CONTENT_TYPE);
        }
        securityService.getLoginUser().orElseThrow(() -> new CustomException(CustomExceptionCode.USER_UNAUTHORIZED));
        Comment.DomainResponse updatedComment = commentService.updateComment(comment).toResponse();
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Boolean> deleteCommentById(@PathVariable("commentId") int commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity.status(NO_CONTENT).build();
    }
}
