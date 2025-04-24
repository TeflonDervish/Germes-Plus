package ru.germes.plus.site.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.germes.plus.site.enums.Role;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.repository.IndividualPeronRepository;

import java.util.Set;

@Configuration
public class AppConfig {

    @Autowired
    private IndividualPeronRepository individualPeronRepository;

    @Bean
    public ApplicationRunner initializeUsers(IndividualPeronRepository individualPeronRepository,
                                             PasswordEncoder passwordEncoder) {
        return args -> {
            initializeUserIfNotExists(
                    "admin@mail.ru",
                    "admin123",
                    Set.of(Role.ADMIN, Role.USER),
                    individualPeronRepository,
                    passwordEncoder
            );

            initializeUserIfNotExists(
                    "user@mail.ru",
                    "user123",
                    Set.of(Role.USER),
                    individualPeronRepository,
                    passwordEncoder
            );
        };
    }

    private void initializeUserIfNotExists(String email,
                                           String rawPassword,
                                           Set<Role> roles,
                                           IndividualPeronRepository individualPeronRepository,
                                           PasswordEncoder passwordEncoder) {
        if (!individualPeronRepository.existsByEmail(email)) {
            IndividualPerson user = new IndividualPerson();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(rawPassword));
            user.setRoles(roles);
            individualPeronRepository.save(user);
        }
    }

}
