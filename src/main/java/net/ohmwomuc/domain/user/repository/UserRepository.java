package net.ohmwomuc.domain.user.repository;

import net.ohmwomuc.domain.user.dto.UserInfo;

public interface UserRepository {
    UserInfo.Domain getUserInfo(Integer userId);

    void createUserInfo(UserInfo.Domain newUser);

    void updateUserNickname(UserInfo.Domain domain);

    void addUserImage(UserInfo.Image image);

    void deleteUser(Integer userId);

    void clearUserImage(Integer userId);

    UserInfo.Image getUserImage(Integer userId);

    void updateUserEmail(UserInfo.Domain domain);

    Boolean checkDuplicateEmail(String email);
}
