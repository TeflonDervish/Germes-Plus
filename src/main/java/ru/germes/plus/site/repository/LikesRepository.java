package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.germes.plus.site.model.Likes;
import ru.germes.plus.site.model.persons.IndividualPerson;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query("SELECT l " +
            "FROM Likes l " +
            "WHERE l.productForIndividual.id = :productId AND l.individualPerson.id = :userId")
    Optional<Likes> checkIsLiked(Long productId, Long userId);

    List<Likes> findByIndividualPerson(IndividualPerson individualPerson);

}
