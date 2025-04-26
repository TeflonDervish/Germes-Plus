package ru.germes.plus.site.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.repository.ProductForIndividualRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductForIndividualService {

    private static final Log log = LogFactory.getLog(ProductForIndividualService.class);
    @Autowired
    private ProductForIndividualRepository productForIndividualRepository;


    public List<ProductForIndividual> getAll() {
        return productForIndividualRepository.findAll();
    }

    public ProductForIndividual getById(Long id) {
        log.info("Получение продукта по " + id);
        return productForIndividualRepository.findById(id).orElse(new ProductForIndividual());
    }



}
