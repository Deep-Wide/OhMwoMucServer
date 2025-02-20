package net.ohmwomuc.domain.user.service;

import net.ohmwomuc.domain.user.dto.UserInfo;

public interface UserService {
    UserInfo.Domain getUserInfo(Integer userId);

    void updateUserNickname(UserInfo.Domain request);

    void addUserImage(UserInfo.Image image, Integer userId);

    void deleteUser(Integer userId);

    UserInfo.Image getUserImage(Integer userId);
}
