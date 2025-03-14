package net.ohmwomuc.core.security.dto;

import lombok.*;
import net.ohmwomuc.core.security.constant.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static net.ohmwomuc.core.security.constant.UserRole.SYS_ADMIN;

public class User {

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
        private boolean rememberMe;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserAccountInfo implements UserDetails {
        private int id;
        private String email;
        private String password;
        private String nickname;
        private Set<GrantedAuthority> role;

        public void setRole(int roleId) {
            this.role = Set.of(new SimpleGrantedAuthority(UserRole.getUserRoleById(roleId).toString()));
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return role;
        }

        @Override
        public String getUsername() {
            return email;
        }

        public UserResponse toResponse() {
            Set<String> authoritiesSet = role.stream()
                    .map(r -> r.getAuthority())
                    .collect(Collectors.toSet());

            return UserResponse.builder()
                    .id(id)
                    .email(email)
                    .nickname(nickname)
                    .role(authoritiesSet)
                    .isSysAdmin(authoritiesSet.stream().anyMatch(a -> a.equals(SYS_ADMIN.toString())))
                    .build();
        }
    }

}
