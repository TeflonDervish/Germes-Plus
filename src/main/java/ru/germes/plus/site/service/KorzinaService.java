package ru.germes.plus.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.Korzina;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.repository.KorzinaRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class KorzinaService {

    @Autowired
    private KorzinaRepository korzinaRepository;

    @Autowired
    private ProductForIndividualService productService;

    public Korzina getKorzina(IndividualPerson individualPerson) {
        Korzina korzina = korzinaRepository.findByIndividualPerson(individualPerson);

        if (korzina == null)
            korzina = createKorzina(individualPerson);

        return korzina;
    }

    public Korzina createKorzina(IndividualPerson individualPerson) {
        Korzina korzina = new Korzina();
        korzina.setIndividualPerson(individualPerson);
        korzina.setProducts(new ArrayList<>());
        return korzinaRepository.save(korzina);
    }

    public Korzina addProduct(Long productId, IndividualPerson individualPerson) {
        ProductForIndividual product = productService.getById(productId);
        Korzina korzina = getKorzina(individualPerson);
        if (korzina == null) {
            korzina = createKorzina(individualPerson);
        }
        korzina.addProduct(product);
        return korzinaRepository.save(korzina);
    }
}
