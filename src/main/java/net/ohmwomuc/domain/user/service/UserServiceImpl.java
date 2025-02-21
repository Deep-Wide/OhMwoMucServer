package net.ohmwomuc.domain.user.service;

import lombok.RequiredArgsConstructor;
import net.ohmwomuc.domain.user.dto.UserInfo;
import net.ohmwomuc.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserInfo.Domain getUserInfo(Integer userId) {
        return userRepository.getUserInfo(userId);
    }

    @Override
    public void updateUserNickname(UserInfo.Domain domain) {
        userRepository.updateUserNickname(domain);
    }

    @Override
    @Transactional
    public void addUserImage(UserInfo.Image image, Integer userId) {
        if (Objects.nonNull(userRepository.getUserImage(userId)))
            userRepository.clearUserImage(userId);
        userRepository.addUserImage(image, userId);
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.deleteUser(userId);
    }

    @Override
    public UserInfo.Image getUserImage(Integer userId) {
        return userRepository.getUserImage(userId);
    }

    @Override
    public void updateUserEmail(UserInfo.Domain domain) {
        userRepository.updateUserEmail(domain);
    }
}
