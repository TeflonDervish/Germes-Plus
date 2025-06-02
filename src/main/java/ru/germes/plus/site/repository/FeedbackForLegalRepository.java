package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.germes.plus.site.model.feedbacks.FeedbackOnProductForIndividual;
import ru.germes.plus.site.model.feedbacks.FeedbackOnProductForLegal;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;

import java.util.List;

@Repository
public interface FeedbackForLegalRepository extends JpaRepository<FeedbackOnProductForLegal, Long> {

    List<FeedbackOnProductForLegal> findByProductForLegal(ProductForLegal productForLegal);

}
