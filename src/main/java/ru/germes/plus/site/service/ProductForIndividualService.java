package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.dto.FilterProductForIndividual;
import ru.germes.plus.site.exceptions.ProductForIndividualException;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.repository.ProductForIndividualRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductForIndividualService {

    private static final Log log = LogFactory.getLog(ProductForIndividualService.class);
    private ProductForIndividualRepository productForIndividualRepository;


    public List<ProductForIndividual> getAll() {
        log.info("Получение списка всех товаров");
        return productForIndividualRepository.findAll();
    }

    public ProductForIndividual getById(Long id) {
        log.info("Получение продукта по " + id);
        return productForIndividualRepository.findById(id).orElseThrow(
                () -> new ProductForIndividualException("Продукт не найден"));
    }

    public List<ProductForIndividual> getBySearch(String search) {
        log.info("Поиск по имени");
        return productForIndividualRepository.findByNameContainingIgnoreCase(search);
    }

    public List<ProductForIndividual> getFilteredProducts(FilterProductForIndividual filter) {
        log.info("Фильтрация продуктов");
        log.info(filter.toString());
        return productForIndividualRepository.findWithFilters(
                filter.getMinPrice(),
                filter.getMaxPrice(),

                filter.getDepthMin(),
                filter.getDepthMax(),

                filter.getPlantingMin(),
                filter.getPlantingMax(),

                filter.getSeatDepthMin(),
                filter.getSeatDepthMax(),

                filter.getSizeMin(),
                filter.getSizeMax(),

                filter.getSleepingSpaceMin(),
                filter.getSleepingSpaceMax(),

                filter.getConfigurations(),
                filter.getMechanisms(),
                filter.getFillings(),
                filter.getArmrests(),
                filter.getBox()
        );
    }


    public List<ProductForIndividual> getBySort(String sort) {
        log.info("Сортировка продуктов по цене");
        List<ProductForIndividual> productForIndividuals;
        if (sort.equals("price_asc")) {
            productForIndividuals = productForIndividualRepository.findAllByOrderByPriceAsc();
        } else {
            productForIndividuals = productForIndividualRepository.findAllByOrderByPriceDesc();
        }
        return productForIndividuals;
    }
}
