package net.ohmwomuc.core.security.authentication;

import lombok.*;
import net.ohmwomuc.core.security.dto.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomAuthenticationToken implements Authentication {
    private User.Principal principal;
    private boolean authenticated;
    private String credentials;
    private Collection<? extends GrantedAuthority> authorities;
    private String details;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Optional.ofNullable(principal)
                .map(User.Principal::getRole)
                .orElse(Set.of());
    }

    @Override
    public String getName() {
        return Optional.ofNullable(principal)
                .map(User.Principal::getEmail)
                .orElse(null);
    }
}
