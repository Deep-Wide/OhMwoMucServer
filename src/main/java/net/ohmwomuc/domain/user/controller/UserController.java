package net.ohmwomuc.domain.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.ohmwomuc.domain.user.dto.UserInfo;
import net.ohmwomuc.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static org.apache.catalina.util.XMLWriter.NO_CONTENT;
import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "User컨트롤러")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfo.Response> getUserInfo(@PathVariable Integer userId) {
        UserInfo.Domain result = userService.getUserInfo(userId);
        return ResponseEntity.ok(result.toResponse());
    }

    @PatchMapping("/{userId}/nickname")
    public ResponseEntity<Void> updateUserNickname(@PathVariable Integer userId, @RequestBody UserInfo.Request request) {
        UserInfo.Domain domain = UserInfo.Request
                .builder()
                .id(userId)
                .nickname(request.getNickname())
                .build().toDomain();
        userService.updateUserNickname(domain);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{userId}/email")
    public ResponseEntity<Void> updateUserEmail(@PathVariable Integer userId, @RequestBody UserInfo.Request request) {
        UserInfo.Domain domain = UserInfo.Request
                .builder()
                .id(userId)
                .email(request.getEmail())
                .build().toDomain();
        userService.updateUserEmail(domain);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{userId}/image")
    public ResponseEntity<UserInfo.Image> getUserImage(@PathVariable Integer userId) {
        UserInfo.Image image = userService.getUserImage(userId);
        return ResponseEntity.ok(image);
    }

    @PostMapping("/{userId}/image")
    public ResponseEntity<Void> addUserImage(@RequestBody UserInfo.Image image, @PathVariable Integer userId) {
        if (!Objects.nonNull(image.getUniqueFileName()))
            return ResponseEntity.badRequest().build();
        userService.addUserImage(image, userId);
        return ResponseEntity.status(CREATED).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.status(NO_CONTENT).build();
    }

}
