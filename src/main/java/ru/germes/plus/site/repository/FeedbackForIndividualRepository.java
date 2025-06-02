package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.germes.plus.site.model.feedbacks.FeedbackOnProductForIndividual;
import ru.germes.plus.site.model.products.ProductForIndividual;

import java.util.List;

@Repository
public interface FeedbackForIndividualRepository extends JpaRepository<FeedbackOnProductForIndividual, Long> {

    List<FeedbackOnProductForIndividual> findByProductForIndividual(ProductForIndividual productForIndividual);

}
