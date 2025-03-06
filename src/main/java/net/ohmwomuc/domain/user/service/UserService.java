package net.ohmwomuc.domain.user.service;

import net.ohmwomuc.domain.user.dto.UserInfo;

public interface UserService {
    UserInfo.Domain getUserInfo(Integer userId);

    UserInfo.Domain createUser(UserInfo.Domain newUser);

    void updateUserNickname(UserInfo.Domain request);

    void addUserImage(UserInfo.Image image, Integer userId);

    void deleteUser(Integer userId);

    UserInfo.Image getUserImage(Integer userId);

    void updateUserEmail(UserInfo.Domain domain);

    Boolean checkDuplicateEmail(String email);

}
