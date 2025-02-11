package net.ohmwomuc.domain.likes.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.ohmwomuc.core.exception.CustomException;
import net.ohmwomuc.core.exception.CustomExceptionCode;
import net.ohmwomuc.core.security.service.SecurityService;
import net.ohmwomuc.domain.likes.dto.Like;
import net.ohmwomuc.domain.likes.service.LikesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequestMapping("api/likes")
@RestController
@RequiredArgsConstructor
@Tag(name = "Likes", description = "Likes컨트롤러")
public class LikesController {

    private final LikesService likesService;
    private final SecurityService securityService;

    @GetMapping("/{muamucId}")
    public ResponseEntity<Like.DomainResponse> getLikes(@PathVariable("muamucId") Integer muamucId) {
        Like.DomainResponse likes = Like.DomainResponse.builder()
                .likesCount(likesService.getLikesCountByMuamucId(muamucId))
                .muamucId(muamucId)
                .build();

        return ResponseEntity.ok(likes);
    }

    @PostMapping("")
    public ResponseEntity<Boolean> reverseLike(@RequestBody Like.DomainRequest like) {
        securityService.getLoginUser().orElseThrow(() -> new CustomException(CustomExceptionCode.USER_UNAUTHORIZED));

        return ResponseEntity.ok(likesService.reverseLike(like));
    }

//    @DeleteMapping("/{muamucId}/{userId}")
//    public ResponseEntity<Boolean> deleteLike(@PathVariable("muamucId") Integer muamucId, @PathVariable("userId") Integer userId) {
//        securityService.getLoginUser().orElseThrow(() -> new CustomException(CustomExceptionCode.USER_UNAUTHORIZED));
//
//        Like.DomainRequest deleteTargetLike = Like.DomainRequest.builder()
//                .muamuc_id(muamucId)
//                .user_id(userId)
//                .build();
//        likesService.deleteLike(deleteTargetLike);
//        return ResponseEntity.status(NO_CONTENT).build();
//    }

}
