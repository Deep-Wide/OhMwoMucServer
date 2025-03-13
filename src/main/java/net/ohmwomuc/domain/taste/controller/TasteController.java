package net.ohmwomuc.domain.taste.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import net.ohmwomuc.core.exception.CustomException;
import net.ohmwomuc.core.exception.CustomExceptionCode;
import net.ohmwomuc.core.security.dto.User;
import net.ohmwomuc.core.security.service.SecurityService;
import net.ohmwomuc.domain.taste.dto.Taste;
import net.ohmwomuc.domain.taste.service.TasteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/taste")
@RestController
@RequiredArgsConstructor
@Tag(name = "taste", description = "taste 컨트롤러")
public class TasteController {

    private SecurityService securityService;
    private TasteService tasteService;

    @PostMapping("")
    public ResponseEntity<Void> addTaste(@RequestBody Taste.Request tasteInfo) {

        Taste.Domain taste = Taste.Domain.builder()
                .restaurantId(tasteInfo.getRestaurantId())
                .tasteCode(tasteInfo.getTasteCode())
                .userId(
                        securityService.getLoginUser().orElseThrow(() -> new CustomException(CustomExceptionCode.USER_UNAUTHORIZED)).getId())
                .build();

        tasteService.addTaste(taste);

        return ResponseEntity.ok().build();
    }
}
