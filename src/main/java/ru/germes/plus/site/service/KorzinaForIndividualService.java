package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.korzina.KorzinaForIndividual;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.repository.KorzinaForIndividualRepository;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class KorzinaForIndividualService {

    private static final Log log = LogFactory.getLog(KorzinaForIndividualService.class);
    private KorzinaForIndividualRepository korzinaForIndividualRepository;
    private ProductForIndividualService productService;

    public KorzinaForIndividual getKorzina(IndividualPerson individualPerson) {
        KorzinaForIndividual korzinaForIndividual = korzinaForIndividualRepository.findByIndividualPerson(individualPerson);

        if (korzinaForIndividual == null)
            korzinaForIndividual = createKorzina(individualPerson);

        return korzinaForIndividual;
    }

    public KorzinaForIndividual createKorzina(IndividualPerson individualPerson) {
        log.info("Создание корзины для пользователя");
        KorzinaForIndividual korzinaForIndividual = new KorzinaForIndividual();
        korzinaForIndividual.setIndividualPerson(individualPerson);
        korzinaForIndividual.setProducts(new ArrayList<>());
        return korzinaForIndividualRepository.save(korzinaForIndividual);
    }

    public KorzinaForIndividual addProduct(Long productId, IndividualPerson individualPerson) {
        log.info("Добавление продукта в корзину");
        ProductForIndividual product = productService.getById(productId);
        KorzinaForIndividual korzinaForIndividual = getKorzina(individualPerson);
        korzinaForIndividual.addProduct(product);
        return korzinaForIndividualRepository.save(korzinaForIndividual);
    }

    public KorzinaForIndividual deleteProduct(Long productId, IndividualPerson individualPerson) {
        log.info("Удаление продукта из корзины");
        ProductForIndividual product = productService.getById(productId);
        KorzinaForIndividual korzinaForIndividual = getKorzina(individualPerson);
        korzinaForIndividual.deleteProduct(product);
        return korzinaForIndividualRepository.save(korzinaForIndividual);
    }

    public boolean isInKorzina(Long productId, IndividualPerson person) {
        log.info("Проверка, находится ли продукт в корзине");
        ProductForIndividual product =  productService.getById(productId);
        KorzinaForIndividual korzinaForIndividual = getKorzina(person);
        return korzinaForIndividual.isInKorzina(product);
    }

    public KorzinaForIndividual clear(IndividualPerson person) {
        KorzinaForIndividual korzinaForIndividual = getKorzina(person);
        korzinaForIndividual.getProducts().clear();
        return korzinaForIndividualRepository.save(korzinaForIndividual);
    }
}
