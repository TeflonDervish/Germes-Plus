package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.germes.plus.site.model.korzina.KorzinaForIndividual;
import ru.germes.plus.site.model.persons.IndividualPerson;

@Repository
public interface KorzinaRepository extends JpaRepository<KorzinaForIndividual, Long> {

    KorzinaForIndividual findByIndividualPerson(IndividualPerson individualPerson);
}
