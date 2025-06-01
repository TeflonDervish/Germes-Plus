package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.germes.plus.site.model.persons.LegalPerson;

import java.util.Optional;

public interface LegalPersonRepository extends JpaRepository<LegalPerson, Long> {

  Optional<LegalPerson> findByEmail(String email);
}