package net.ohmwomuc.core.security.repository;

import net.ohmwomuc.core.security.dto.User;

public interface SecurityRepository {
    User.UserAccountInfo findUserByEmail(String name);
}
