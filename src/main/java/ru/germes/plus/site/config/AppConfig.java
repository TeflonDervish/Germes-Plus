package ru.germes.plus.site.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.germes.plus.site.enums.Role;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.repository.IndividualPersonRepository;

import java.util.Set;

@Configuration
@AllArgsConstructor
public class AppConfig {

    private IndividualPersonRepository individualPersonRepository;

    @Bean
    public ApplicationRunner initializeUsers(IndividualPersonRepository individualPersonRepository,
                                             PasswordEncoder passwordEncoder) {
        return args -> {
            initializeUserIfNotExists(
                    "admin@mail.ru",
                    "admin123",
                    "admin",
                    individualPersonRepository,
                    passwordEncoder
            );

            initializeUserIfNotExists(
                    "user@mail.ru",
                    "user123",
                    "user",
                    individualPersonRepository,
                    passwordEncoder
            );
        };
    }

    private void initializeUserIfNotExists(String email,
                                           String rawPassword,
                                           String name,
//                                           String
                                           IndividualPersonRepository individualPersonRepository,
                                           PasswordEncoder passwordEncoder) {
        if (!individualPersonRepository.existsByEmail(email)) {
            IndividualPerson user = new IndividualPerson();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(rawPassword));
            individualPersonRepository.save(user);
        }
    }

}
