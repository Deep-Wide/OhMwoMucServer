package net.ohmwomuc.domain.forks.controller;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.core.exception.CustomException;
import net.ohmwomuc.core.exception.CustomExceptionCode;
import net.ohmwomuc.core.security.service.SecurityService;
import net.ohmwomuc.domain.forks.dto.Fork;
import net.ohmwomuc.domain.forks.service.ForkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/fork")
@RestController
@RequiredArgsConstructor
public class ForkController {
    private final ForkService forkService;
    private final SecurityService securityService;

    @PostMapping("")
    public ResponseEntity<Boolean> addFork(@RequestBody Fork fork) {
        securityService.getLoginUser().orElseThrow(() -> new CustomException(CustomExceptionCode.USER_UNAUTHORIZED));

        return ResponseEntity.ok(forkService.reverseFork(fork));
    }
}
