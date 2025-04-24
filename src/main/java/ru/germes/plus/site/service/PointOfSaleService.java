package ru.germes.plus.site.service;

import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.repository.PointOfSaleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PointOfSaleService {

    private PointOfSaleRepository pointOfSaleRepository;

    private PointOfSaleService(PointOfSaleRepository pointOfSaleRepository) {
        this.pointOfSaleRepository = pointOfSaleRepository;
    }

    public PointOfSale getById(Long id) {
        return pointOfSaleRepository.findById(id).orElse(new PointOfSale());
    }

    public List<PointOfSale> getAll() {
        return pointOfSaleRepository.findAll();
    }
}
