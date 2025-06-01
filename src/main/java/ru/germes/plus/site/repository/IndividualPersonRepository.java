package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.germes.plus.site.model.persons.IndividualPerson;

import java.util.Optional;

@Repository
public interface IndividualPersonRepository extends JpaRepository<IndividualPerson, Long> {

    boolean existsByEmail(String email);

    Optional<IndividualPerson> findByEmail(String email);
}
