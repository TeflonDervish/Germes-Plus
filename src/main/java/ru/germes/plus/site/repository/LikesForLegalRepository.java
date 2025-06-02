package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.germes.plus.site.model.likes.LikesForIndividual;
import ru.germes.plus.site.model.likes.LikesForLegal;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;

import java.util.List;
import java.util.Optional;

public interface LikesForLegalRepository extends JpaRepository<LikesForLegal, Long> {

    @Query("SELECT l " +
            "FROM LikesForLegal l " +
            "WHERE l.productForLegal.id = :productId AND l.legalPerson.id = :userId")
    Optional<LikesForLegal> checkIsLiked(Long productId, Long userId);

    List<LikesForLegal> findByLegalPerson(LegalPerson legalPerson);

}
