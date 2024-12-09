package ru.ssau.tk.jabalab.lr2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ssau.tk.jabalab.lr2.model.Role;
import ru.ssau.tk.jabalab.lr2.model.User;
import ru.ssau.tk.jabalab.lr2.repository.UserRepository;


@SpringBootApplication
public class LR5Application {
    public static void main(String[] args) {
        SpringApplication.run(LR5Application.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return args -> {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRole(Role.USER);
            userRepository.save(user);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        };
    }

}
