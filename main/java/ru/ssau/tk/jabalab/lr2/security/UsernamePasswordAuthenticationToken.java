/*package ru.ssau.tk.jabalab.lr2.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.ssau.tk.jabalab.lr2.model.User;

import java.util.ArrayList;
import java.util.List;

public class UsernamePasswordAuthenticationToken (String name, String password) {
    final List<GrantedAuthority> grantedAuths = new ArrayList<>();
    grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
    final UserDetails principal = new User(username, password, grantedAuths);
    return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
}*/
