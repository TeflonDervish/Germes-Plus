package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.dto.FilterProductForLegal;
import ru.germes.plus.site.exceptions.ProductForIndividualException;
import ru.germes.plus.site.model.products.ProductForLegal;
import ru.germes.plus.site.repository.ProductForLegalRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductForLegalService {

    private static final Log log = LogFactory.getLog(ProductForLegalService.class);
    private ProductForLegalRepository productForLegalRepository;


    public List<ProductForLegal> getAll() {
        log.info("Получение списка всех товаров");
        return productForLegalRepository.findAll();
    }

    public ProductForLegal getById(Long id) {
        log.info("Получение продукта по " + id);
        return productForLegalRepository.findById(id).orElseThrow(
                () -> new ProductForIndividualException("Продукт не найден"));
    }

    public List<ProductForLegal> getBySearch(String search) {
        log.info("Поиск по имени");
        return productForLegalRepository.findByNameContainingIgnoreCase(search);
    }

//    public List<ProductForLegal> getFilteredProducts(FilterProductForLegal filter) {
//        log.info("Фильтрация продуктов");
//        log.info(filter.toString());
//        return productForLegalRepository.findWithFilters(
//                filter.getMinPrice(),
//                filter.getMaxPrice(),
//                filter.getConfigurations(),
//                filter.getMechanisms(),
//                filter.getFillings()
//        );
//    }


    public List<ProductForLegal> getBySort(String sort) {
        log.info("Сортировка продуктов по цене");
        List<ProductForLegal> product;
        if (sort.equals("price_asc")) {
            product = productForLegalRepository.findAllByOrderByPriceAsc();
        } else {
            product = productForLegalRepository.findAllByOrderByPriceDesc();
        }
        return product;
    }

    public List<ProductForLegal> getFilteredProducts(FilterProductForLegal filter) {
        log.info("Фильтрация продуктов");
        log.info(filter.toString());
        return productForLegalRepository.findWithFilters(
                filter.getMinPrice(),
                filter.getMaxPrice(),
                filter.getConfigurations());
    }
}
