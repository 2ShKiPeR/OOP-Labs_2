package ru.ssau.tk.jabalab.lr2.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.ssau.tk.jabalab.lr2.entity.User;

import java.util.Collection;
import java.util.List;

@Data
@RequiredArgsConstructor
public class DefaultUserDetails implements UserDetails {

    private final int id;

    private final String username;

    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public static DefaultUserDetails build(User user) {
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        return new DefaultUserDetails(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

}