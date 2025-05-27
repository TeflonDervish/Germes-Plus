package ru.germes.plus.site.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.Korzina;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.repository.KorzinaRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class KorzinaService {

    private static final Log log = LogFactory.getLog(KorzinaService.class);
    private KorzinaRepository korzinaRepository;
    private ProductForIndividualService productService;

    public Korzina getKorzina(IndividualPerson individualPerson) {
        Korzina korzina = korzinaRepository.findByIndividualPerson(individualPerson);

        if (korzina == null)
            korzina = createKorzina(individualPerson);

        return korzina;
    }

    public Korzina createKorzina(IndividualPerson individualPerson) {
        log.info("Создание корзины для пользователя");
        Korzina korzina = new Korzina();
        korzina.setIndividualPerson(individualPerson);
        korzina.setProducts(new ArrayList<>());
        return korzinaRepository.save(korzina);
    }

    public Korzina addProduct(Long productId, IndividualPerson individualPerson) {
        log.info("Добавление продукта в корзину");
        ProductForIndividual product = productService.getById(productId);
        Korzina korzina = getKorzina(individualPerson);
        korzina.addProduct(product);
        return korzinaRepository.save(korzina);
    }

    public Korzina deleteProduct(Long productId, IndividualPerson individualPerson) {
        log.info("Удаление продукта из корзины");
        ProductForIndividual product = productService.getById(productId);
        Korzina korzina = getKorzina(individualPerson);
        korzina.deleteProduct(product);
        return korzinaRepository.save(korzina);
    }

    public boolean isInKorzina(Long productId, IndividualPerson person) {
        log.info("Проверка, находится ли продукт в корзине");
        ProductForIndividual product =  productService.getById(productId);
        Korzina korzina = getKorzina(person);
        return korzina.isInKorzina(product);
    }

    public Korzina clear(IndividualPerson person) {
        Korzina korzina = getKorzina(person);
        korzina.getProducts().clear();
        return korzinaRepository.save(korzina);
    }
}
