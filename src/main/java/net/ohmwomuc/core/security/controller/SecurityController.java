package net.ohmwomuc.core.security.controller;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.core.security.dto.User;
import net.ohmwomuc.core.security.service.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
@RequiredArgsConstructor
public class SecurityController {

    private final SecurityService securityService;

    @GetMapping("/login-user")
    public ResponseEntity<User.Principal> getLoginUser() {
        return ResponseEntity.ok(securityService.getLoginUser().orElse(null));
    }

}
