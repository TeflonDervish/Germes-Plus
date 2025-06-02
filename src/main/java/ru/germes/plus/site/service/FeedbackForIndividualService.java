package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.feedbacks.FeedbackOnProductForIndividual;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.repository.FeedbackForIndividualRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackForIndividualService {

    private static final Log log = LogFactory.getLog(FeedbackForIndividualService.class);
    private FeedbackForIndividualRepository feedbackForIndividualRepository;
    private ProductForIndividualService productForIndividualService;

    public FeedbackOnProductForIndividual sendFeedback(Long productId, IndividualPerson individualPerson, String text) {
        log.info("Оставление отзыва");

        ProductForIndividual productForIndividual = productForIndividualService.getById(productId);

        FeedbackOnProductForIndividual feedbackOnProductForIndividual = new FeedbackOnProductForIndividual();
        feedbackOnProductForIndividual.setIndividualPerson(individualPerson);
        feedbackOnProductForIndividual.setProductForIndividual(productForIndividual);
        feedbackOnProductForIndividual.setText(text);
        feedbackOnProductForIndividual.setGrade(5.0);
        feedbackOnProductForIndividual.setDate(LocalDate.now());

        return feedbackForIndividualRepository.save(feedbackOnProductForIndividual);
    }

    public List<FeedbackOnProductForIndividual> getByProductForIndividual(Long productId) {
        log.info("Получение продуктов " + productId);
        ProductForIndividual product = productForIndividualService.getById(productId);
        return feedbackForIndividualRepository.findByProductForIndividual(product);
    }
}
