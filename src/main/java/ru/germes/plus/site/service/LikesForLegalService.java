package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.exceptions.ProductForIndividualException;
import ru.germes.plus.site.model.likes.LikesForIndividual;
import ru.germes.plus.site.model.likes.LikesForLegal;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;
import ru.germes.plus.site.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LikesForLegalService {

    private static final Log log = LogFactory.getLog(LikesForLegalService.class);
    private ProductForLegalRepository productForLegalRepository;

    private LegalPersonRepository legalPersonRepository;

    private LikesForLegalRepository likesForLegalRepository;

    public LikesForLegal addLike(Long productId, LegalPerson legalPerson) {
        log.info("Поставлен лайк");
        ProductForLegal productForIndividual = productForLegalRepository.findById(productId)
                .orElseThrow(() -> new ProductForIndividualException("Не найден такой продукт"));

        LikesForLegal like = new LikesForLegal();
        like.setLegalPerson(legalPerson);
        like.setProductForLegal(productForIndividual);

        return likesForLegalRepository.save(like);
    }

    public void deleteLike(Long productId, Long userId) {
        log.info("Удален лайк");
        LikesForLegal like = getLike(productId, userId)
                .orElseThrow(() -> new RuntimeException("Лайк не найден"));

        likesForLegalRepository.delete(like);
    }

    public Optional<LikesForLegal> getLike(Long productId, Long userId){
        log.info("Получение лайков");
        return likesForLegalRepository.checkIsLiked(productId, userId);
    }

    @Query
    public List<ProductForLegal> getProductForIndividuals(LegalPerson individualPerson) {
        log.info("Получение продукта пользователя");
        return likesForLegalRepository.findByLegalPerson(individualPerson)
                .stream()
                .map(LikesForLegal::getProductForLegal)
                .toList();
    }
}
