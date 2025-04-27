package ru.germes.plus.site.service;

import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private IndividualPersonRepository individualPersonRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return individualPersonRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователя с таким именем не существует"));
    }

    public void registerIndividualPerson(ru.germes.plus.site.model.persons.IndividualPerson individualPerson) {
        ru.germes.plus.site.model.persons.IndividualPerson newIndividualPerson = new ru.germes.plus.site.model.persons.IndividualPerson();
        newIndividualPerson.setEmail(individualPerson.getEmail());
        newIndividualPerson.setPassword(passwordEncoder.encode(individualPerson.getPassword()));
        individualPersonRepository.save(newIndividualPerson);
    }

    public IndividualPerson save(IndividualPerson individualPerson) {
        return individualPersonRepository.save(individualPerson);
    }
}
