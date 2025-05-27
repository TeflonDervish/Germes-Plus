package ru.germes.plus.site.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.repository.IndividualPersonRepository;

@Service
public class IndividualPersonService implements UserDetailsService {
    private static final Log log = LogFactory.getLog(IndividualPersonService.class);
    private final IndividualPersonRepository individualPersonRepository;

    private final PasswordEncoder passwordEncoder;

    public IndividualPersonService(IndividualPersonRepository individualPersonRepository,
                                   PasswordEncoder passwordEncoder) {
        this.individualPersonRepository = individualPersonRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return individualPersonRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователя с таким именем не существует"));
    }

    public void registerIndividualPerson(ru.germes.plus.site.model.persons.IndividualPerson individualPerson) {
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

    public void save(Long id, IndividualPerson newData) {
        IndividualPerson individualPerson = individualPersonRepository.findById(id).orElse(null);
        individualPerson.setEmail(newData.getEmail());
        individualPerson.setCity(newData.getCity());
        individualPerson.setPhone(newData.getPhone());
        individualPerson.setSurname(newData.getSurname());
        individualPerson.setName(newData.getName());
        individualPersonRepository.save(individualPerson);
    }

    public IndividualPerson getById(Long id) {
        return individualPersonRepository.findById(id).orElse(null);
    }
}
