package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.korzina.KorzinaForIndividual;
import ru.germes.plus.site.model.korzina.KorzinaForLegal;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;
import ru.germes.plus.site.repository.KorzinaForLegalRepository;
import ru.germes.plus.site.repository.ProductForLegalRepository;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class KorzinaForLegalService {

    private static final Log log = LogFactory.getLog(KorzinaForLegalService.class);
    private final KorzinaForLegalRepository korzinaForLegalRepository;
    private KorzinaForLegalRepository korzina;
    private ProductForLegalService productService;

    public KorzinaForLegal getKorzina(LegalPerson legalPerson) {
        KorzinaForLegal korzinaForIndividual = korzina.findByLegalPerson(legalPerson);

        if (korzinaForIndividual == null)
            korzinaForIndividual = createKorzina(legalPerson);

        return korzinaForIndividual;
    }

    public KorzinaForLegal createKorzina(LegalPerson legalPerson) {
        log.info("Создание корзины для пользователя");
        KorzinaForLegal korzina = new KorzinaForLegal();
        korzina.setLegalPerson(legalPerson);
        korzina.setProducts(new ArrayList<>());
        return korzinaForLegalRepository.save(korzina);
    }

    public KorzinaForLegal addProduct(Long productId, LegalPerson individualPerson) {
        log.info("Добавление продукта в корзину");
        ProductForLegal product = productService.getById(productId);
        KorzinaForLegal korzina = getKorzina(individualPerson);
        korzina.addProduct(product);
        return korzinaForLegalRepository.save(korzina);
    }

    public KorzinaForLegal deleteProduct(Long productId, LegalPerson legalPerson) {
        log.info("Удаление продукта из корзины");
        ProductForLegal product = productService.getById(productId);
        KorzinaForLegal korzina = getKorzina(legalPerson);
        korzina.deleteProduct(product);
        return korzinaForLegalRepository.save(korzina);
    }

    public boolean isInKorzina(Long productId, LegalPerson person) {
        log.info("Проверка, находится ли продукт в корзине");
        ProductForLegal product =  productService.getById(productId);
        KorzinaForLegal korzinaForIndividual = getKorzina(person);
        return korzinaForIndividual.isInKorzina(product);
    }

    public KorzinaForLegal clear(LegalPerson person) {
        KorzinaForLegal korzinaForIndividual = getKorzina(person);
        korzinaForIndividual.getProducts().clear();
        return korzinaForLegalRepository.save(korzinaForIndividual);
    }
}
