package net.ohmwomuc.core.security.dto;

import lombok.*;
import net.ohmwomuc.core.security.constant.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static net.ohmwomuc.core.security.constant.UserRole.SYS_ADMIN;

public class User {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Principal {
        private int id;
        private String email;
        private String nickname;
        private Set<GrantedAuthority> role;

        public UserResponse toResponse() {
            Set<String> authoritySet = role.stream()
                    .map(r -> r.getAuthority())
                    .collect(Collectors.toSet());

            return UserResponse.builder()
                    .id(id)
                    .email(email)
                    .nickname(nickname)
                    .role(authoritySet)
                    .isSysAdmin(authoritySet.stream().anyMatch(a->a.equals(SYS_ADMIN.toString())))
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserResponse {
        private int id;
        private String email;
        private String nickname;
        private Set<String> role;
        private boolean isSysAdmin;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LoginRequest {
        private String email;
        private String password;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserAccountInfo {
        private int id;
        private String email;
        private String password;
        private String nickname;
        private Set<GrantedAuthority> role;

        public void setRole(int roleId) {
            this.role = Set.of(new SimpleGrantedAuthority(UserRole.getUserRoleById(roleId).toString()));
        }
    }

}
