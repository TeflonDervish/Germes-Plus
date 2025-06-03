package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.repository.LegalPersonRepository;
import ru.germes.plus.site.repository.IndividualPersonRepository;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    
    private static final Log log = LogFactory.getLog(UserService.class);

    private final IndividualPersonRepository individualPersonRepository;
    private final LegalPersonRepository legalPersonRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Получение пользователя по имени");
        IndividualPerson individualPerson = individualPersonRepository.findByEmail(email).orElse(null);
        LegalPerson legalPerson = legalPersonRepository.findByEmail(email).orElse(null);
        log.info(email);
        if (individualPerson == null) return legalPerson;
        else return individualPerson;
    }

    public void registerIndividualPerson(ru.germes.plus.site.model.persons.IndividualPerson individualPerson) {
        log.info("Регистрация пользователя");
        if (!individualPersonRepository.existsByEmail(individualPerson.getEmail())) {
            ru.germes.plus.site.model.persons.IndividualPerson newIndividualPerson = new ru.germes.plus.site.model.persons.IndividualPerson();
            newIndividualPerson.setName(individualPerson.getName());
            newIndividualPerson.setSurname(individualPerson.getSurname());
            newIndividualPerson.setPhone(individualPerson.getPhone());
            newIndividualPerson.setCity(individualPerson.getCity());
            newIndividualPerson.setEmail(individualPerson.getEmail());
            newIndividualPerson.setPassword(passwordEncoder.encode(individualPerson.getPassword()));
            individualPersonRepository.save(newIndividualPerson);
        } else {
            log.warn("Пользователь с таким email уже существует");
        }
    }

    public IndividualPerson save(Long id, IndividualPerson newData) {
        log.info("Сохранение данных пользователя");
        IndividualPerson individualPerson = individualPersonRepository.findById(id).orElse(null);
        individualPerson.setEmail(newData.getEmail());
        individualPerson.setCity(newData.getCity());
        individualPerson.setPhone(newData.getPhone());
        individualPerson.setSurname(newData.getSurname());
        individualPerson.setName(newData.getName());
        return individualPersonRepository.save(individualPerson);
    }

    public LegalPerson save(Long id, LegalPerson newData) {
        log.info("Сохранение данных пользователя");
        LegalPerson legalPerson = legalPersonRepository.findById(id).orElse(null);
        legalPerson.setEmail(newData.getEmail());
        legalPerson.setPhoneNumber(newData.getPhoneNumber());
        legalPerson.setName(newData.getName());
        return legalPersonRepository.save(legalPerson);
    }

    public IndividualPerson getIndividualById(Long id) {
        log.info("Получение пользователя по id");
        return individualPersonRepository.findById(id).orElse(null);
    }

    public LegalPerson getLegalById(Long id) {
        log.info("Получение пользователя по id");
        return legalPersonRepository.findById(id).orElse(null);
    }

    public LegalPerson registerLegalPerson(LegalPerson legalPerson) {
        log.info("Создать пользователя юр лиц");
        LegalPerson newLegalPerson = LegalPerson.builder()
                .name(legalPerson.getName())
                .phoneNumber(legalPerson.getPhoneNumber())
                .email(legalPerson.getEmail())
                .password(passwordEncoder.encode(legalPerson.getPassword()))
                .build();
        return legalPersonRepository.save(newLegalPerson);
    }
}
