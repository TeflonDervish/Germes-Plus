package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.feedbacks.FeedbackOnPoint;
import ru.germes.plus.site.model.feedbacks.FeedbackOnProductForIndividual;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.repository.FeedbackForIndividualRepository;
import ru.germes.plus.site.repository.FeedbackForPointRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackOnPointService {

    private static final Log log = LogFactory.getLog(FeedbackOnPointService.class);
    private FeedbackForPointRepository feedbackForPointRepository;
    private PointOfSaleService pointOfSaleService;

    public FeedbackOnPoint sendFeedback(Long productId, UserDetails user, String text) {
        log.info("Оставление отзыва");

        PointOfSale pointOfSale = pointOfSaleService.getById(productId);

        FeedbackOnPoint feedback = new FeedbackOnPoint();
        feedback.setPointOfSale(pointOfSale);
        if (user instanceof IndividualPerson individualPerson) {
            feedback.setName("Физ лицо: " + individualPerson.getName());
        }else if (user instanceof LegalPerson legalPerson) {
            feedback.setName("Юр лицо: " + legalPerson.getName());
        }
        feedback.setText(text);
        feedback.setGrade(5.0);
        feedback.setDate(LocalDate.now());

        return feedbackForPointRepository.save(feedback);
    }

    public List<FeedbackOnPoint> getByPointOfSale(Long pointId) {
        log.info("Получение продуктов " + pointId);
        PointOfSale point = pointOfSaleService.getById(pointId);
        return feedbackForPointRepository.findByPointOfSale(point);
    }
}
