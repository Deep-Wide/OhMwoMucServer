package net.ohmwomuc.core.security.repository;

import net.ohmwomuc.core.security.dto.User;
import net.ohmwomuc.domain.user.dto.UserInfo;

public interface SecurityRepository {
    User.UserAccountInfo findUserByUsername(String name);

    void createUser(User.UserAccountInfo newUser);

    User.UserAccountInfo findUserByGoogleKey(String sub);

    User.UserAccountInfo findUserByKakaoKey(String kakaoId);
}
