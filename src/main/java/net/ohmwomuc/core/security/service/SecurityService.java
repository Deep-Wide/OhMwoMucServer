package net.ohmwomuc.core.security.service;

import net.ohmwomuc.core.security.dto.User;
import net.ohmwomuc.domain.user.dto.UserInfo;

import java.util.Optional;

public interface SecurityService {
    Optional<User.UserAccountInfo> getLoginUser();

    User.UserAccountInfo createUser(User.UserAccountInfo newUser);

    User.UserAccountInfo loadUserByGoogleKey(String sub);

    User.UserAccountInfo loadUserByKakaoKey(String kakaoKey);
}
