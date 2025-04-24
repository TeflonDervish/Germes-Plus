package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.germes.plus.site.model.persons.IndividualPerson;

import java.util.Optional;

@Repository
public interface IndividualPeronRepository extends JpaRepository<IndividualPerson, Long> {

    Optional<IndividualPerson> findByEmail(String email);

    boolean existsByEmail(String email);
}
