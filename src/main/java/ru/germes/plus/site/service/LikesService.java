package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.exceptions.ProductForIndividualException;
import ru.germes.plus.site.model.likes.LikesForIndividual;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.repository.IndividualPersonRepository;
import ru.germes.plus.site.repository.LikesRepository;
import ru.germes.plus.site.repository.ProductForIndividualRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LikesService {

    private static final Log log = LogFactory.getLog(LikesService.class);
    private ProductForIndividualRepository productForIndividualRepository;

    private IndividualPersonRepository individualPersonRepository;

    private LikesRepository likesRepository;

    public LikesForIndividual addLike(Long productId, IndividualPerson individualPerson) {
        log.info("Поставлен лайк");
        ProductForIndividual productForIndividual = productForIndividualRepository.findById(productId)
                .orElseThrow(() -> new ProductForIndividualException("Не найден такой продукт"));

        LikesForIndividual like = new LikesForIndividual();
        like.setIndividualPerson(individualPerson);
        like.setProductForIndividual(productForIndividual);

        return likesRepository.save(like);
    }

    public void deleteLike(Long productId, Long userId) {
        log.info("Удален лайк");
        LikesForIndividual like = getLike(productId, userId)
                .orElseThrow(() -> new RuntimeException("Лайк не найден"));

        likesRepository.delete(like);
    }

    public Optional<LikesForIndividual> getLike(Long productId, Long userId){
        log.info("Получение лайков");
        return likesRepository.checkIsLiked(productId, userId);
    }

    @Query
    public List<ProductForIndividual> getProductForIndividuals(IndividualPerson individualPerson) {
        log.info("Получение продукта пользователя");
        return likesRepository.findByIndividualPerson(individualPerson)
                .stream()
                .map(LikesForIndividual::getProductForIndividual)
                .toList();
    }
}
