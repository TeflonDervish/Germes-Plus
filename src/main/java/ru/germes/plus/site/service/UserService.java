package ru.germes.plus.site.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.repository.IndividualPeronRepository;

@Service
public class UserService implements UserDetailsService  {
    @Autowired
    private IndividualPeronRepository individualPeronRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return individualPeronRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователя с таким именем не существует"));
    }

    public void registerIndividualPerson(IndividualPerson individualPerson) {
        IndividualPerson newIndividualPerson = new IndividualPerson();
        newIndividualPerson.setEmail(individualPerson.getEmail());
        newIndividualPerson.setPassword(passwordEncoder.encode(individualPerson.getPassword()));
        individualPeronRepository.save(newIndividualPerson);
    }
}
