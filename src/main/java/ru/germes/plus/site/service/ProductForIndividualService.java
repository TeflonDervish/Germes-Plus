package ru.germes.plus.site.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.exceptions.ProductForIndividualException;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.repository.ProductForIndividualRepository;

import java.util.List;

@Service
public class ProductForIndividualService {

    @Autowired
    private ProductForIndividualRepository productForIndividualRepository;

    public List<ProductForIndividual> getAll() {
        return productForIndividualRepository.findAll();
    }

    public ProductForIndividual getById(Long id) {
        return productForIndividualRepository.findById(id)
                .orElseThrow(() -> new ProductForIndividualException("Продукт с таким id не найден"));
    }

}
