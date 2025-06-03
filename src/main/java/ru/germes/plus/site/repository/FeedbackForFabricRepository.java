package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.germes.plus.site.model.Fabric;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.feedbacks.FeedbackOnFabric;
import ru.germes.plus.site.model.feedbacks.FeedbackOnPoint;

import java.util.List;

@Repository
public interface FeedbackForFabricRepository extends JpaRepository<FeedbackOnFabric, Long> {

    List<FeedbackOnFabric> findByFabric(Fabric fabric);

}
