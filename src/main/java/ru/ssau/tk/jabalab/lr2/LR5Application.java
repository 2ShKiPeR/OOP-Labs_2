package ru.ssau.tk.jabalab.lr2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ssau.tk.jabalab.lr2.entity.Role;
import ru.ssau.tk.jabalab.lr2.entity.User;
import ru.ssau.tk.jabalab.lr2.repository.UserRepository;


@SpringBootApplication
public class LR5Application {
    public static void main(String[] args) {
        SpringApplication.run(LR5Application.class, args);
    }
}
