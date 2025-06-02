package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.germes.plus.site.model.korzina.KorzinaForIndividual;
import ru.germes.plus.site.model.korzina.KorzinaForLegal;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;

@Repository
public interface KorzinaForLegalRepository extends JpaRepository<KorzinaForLegal, Long> {
    KorzinaForLegal findByLegalPerson(LegalPerson legalPerson);
}
