package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.germes.plus.site.model.Korzina;
import ru.germes.plus.site.model.persons.IndividualPerson;

import java.util.List;
import java.util.Optional;

@Repository
public interface KorzinaRepository extends JpaRepository<Korzina, Long> {

    Korzina findByIndividualPerson(IndividualPerson individualPerson);
}
