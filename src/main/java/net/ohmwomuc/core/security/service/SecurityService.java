package net.ohmwomuc.core.security.service;

import net.ohmwomuc.core.security.dto.User;

import java.util.Optional;

public interface SecurityService {
    Optional<User.Principal> getLoginUser();
    User.UserAccountInfo loadUserByUserName(String name);
}
