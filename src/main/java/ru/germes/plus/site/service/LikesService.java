package ru.germes.plus.site.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.exceptions.ProductForIndividualException;
import ru.germes.plus.site.model.Likes;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.repository.IndividualPersonRepository;
import ru.germes.plus.site.repository.LikesRepository;
import ru.germes.plus.site.repository.ProductForIndividualRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LikesService {

    private static final Log log = LogFactory.getLog(LikesService.class);
    @Autowired
    private ProductForIndividualRepository productForIndividualRepository;

    @Autowired
    private IndividualPersonRepository individualPersonRepository;

    @Autowired
    private LikesRepository likesRepository;

    public Likes addLike(Long productId, IndividualPerson individualPerson) {
        log.info("Поставлен лайк");
        ProductForIndividual productForIndividual = productForIndividualRepository.findById(productId)
                .orElseThrow(() -> new ProductForIndividualException("Не найден такой продукт"));

        Likes like = new Likes();
        like.setIndividualPerson(individualPerson);
        like.setProductForIndividual(productForIndividual);

        return likesRepository.save(like);
    }

    public void deleteLike(Long productId, Long userId) {
        log.info("Удален лайк");
        Likes like = getLike(productId, userId)
                .orElseThrow(() -> new RuntimeException("Лайк не найден"));

        likesRepository.delete(like);
    }

    public Optional<Likes> getLike(Long productId, Long userId){
        return likesRepository.checkIsLiked(productId, userId);
    }

    @Query
    public List<ProductForIndividual> getProductForIndividuals(IndividualPerson individualPerson) {
        return likesRepository.findByIndividualPerson(individualPerson)
                .stream()
                .map(Likes::getProductForIndividual)
                .toList();
    }
}
