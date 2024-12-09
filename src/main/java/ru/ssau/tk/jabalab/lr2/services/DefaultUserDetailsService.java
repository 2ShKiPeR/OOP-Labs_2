package ru.ssau.tk.jabalab.lr2.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ssau.tk.jabalab.lr2.entity.User;
import ru.ssau.tk.jabalab.lr2.repository.UserRepository;
import ru.ssau.tk.jabalab.lr2.security.DefaultUserDetails;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with username: " + username + " not found"));

        return DefaultUserDetails.build(user);
    }
}