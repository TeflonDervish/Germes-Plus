package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.Fabric;
import ru.germes.plus.site.model.feedbacks.FeedbackOnFabric;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.repository.FeedbackForFabricRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackForFabricService {

    private static final Log log = LogFactory.getLog(FeedbackForFabricService.class);
    private FeedbackForFabricRepository feedbackForFabricRepository;
    private FabricService fabricService;

    public FeedbackOnFabric sendFeedback(Long fabricId, UserDetails user, String text) {
        log.info("Оставление отзыва");

        Fabric fabric = fabricService.getById(fabricId);

        FeedbackOnFabric feedback = new FeedbackOnFabric();
        feedback.setFabric(fabric);
        if (user instanceof IndividualPerson individualPerson) {
            feedback.setName("Физ лицо: " + individualPerson.getName());
        }else if (user instanceof LegalPerson legalPerson) {
            feedback.setName("Юр лицо: " + legalPerson.getName());
        }
        feedback.setText(text);
        feedback.setGrade(5.0);
        feedback.setDate(LocalDate.now());

        return feedbackForFabricRepository.save(feedback);
    }

    public List<FeedbackOnFabric> getByFabricId(Long fabricId) {
        log.info("Получение фабрики " + fabricId);
        Fabric fabric = fabricService.getById(fabricId);
        return feedbackForFabricRepository.findByFabric(fabric);
    }
}
