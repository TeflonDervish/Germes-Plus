package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.germes.plus.site.model.likes.LikesForIndividual;
import ru.germes.plus.site.model.persons.IndividualPerson;

import java.util.List;
import java.util.Optional;

public interface LikesForIndividualRepository extends JpaRepository<LikesForIndividual, Long> {

    @Query("SELECT l " +
            "FROM LikesForIndividual l " +
            "WHERE l.productForIndividual.id = :productId AND l.individualPerson.id = :userId")
    Optional<LikesForIndividual> checkIsLiked(Long productId, Long userId);

    List<LikesForIndividual> findByIndividualPerson(IndividualPerson individualPerson);

}
