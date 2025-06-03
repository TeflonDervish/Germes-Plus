package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.feedbacks.FeedbackOnPoint;
import ru.germes.plus.site.model.feedbacks.FeedbackOnProductForIndividual;
import ru.germes.plus.site.model.products.ProductForIndividual;

import java.util.List;

@Repository
public interface FeedbackForPointRepository extends JpaRepository<FeedbackOnPoint, Long> {

    List<FeedbackOnPoint> findByPointOfSale(PointOfSale pointOfSale);

}
