package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.feedbacks.FeedbackOnPointFromLegal;
import ru.germes.plus.site.model.feedbacks.FeedbackOnProductForIndividual;
import ru.germes.plus.site.model.feedbacks.FeedbackOnProductForLegal;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;
import ru.germes.plus.site.repository.FeedbackForLegalRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackForLegalService {

    private static final Log log = LogFactory.getLog(FeedbackForLegalService.class);
    private FeedbackForLegalRepository feedbackForLegalRepository;
    private ProductForLegalService productForLegalService;

    public FeedbackOnProductForLegal sendFeedback(Long productId, LegalPerson legalPerson, String text) {
        log.info("Оставление отзыва");

        ProductForLegal product = productForLegalService.getById(productId);

        FeedbackOnProductForLegal feedbackOnProductForIndividual = new FeedbackOnProductForLegal();
        feedbackOnProductForIndividual.setLegalPerson(legalPerson);
        feedbackOnProductForIndividual.setProductForLegal(product);
        feedbackOnProductForIndividual.setText(text);
        feedbackOnProductForIndividual.setGrade(5.0);
        feedbackOnProductForIndividual.setDate(LocalDate.now());

        return feedbackForLegalRepository.save(feedbackOnProductForIndividual);
    }

    public List<FeedbackOnProductForLegal> getByProductForIndividual(Long productId) {
        log.info("Получение продуктов " + productId);
        ProductForLegal product = productForLegalService.getById(productId);
        return feedbackForLegalRepository.findByProductForLegal(product);
    }
}
