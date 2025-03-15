package ru.germes.plus.site.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.exceptions.ProductForIndividualException;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.repository.ProductForIndividualRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Map<String, String> getCharacteristicFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<HashMap<String, String>>() {
        }.getType());
    }

}
