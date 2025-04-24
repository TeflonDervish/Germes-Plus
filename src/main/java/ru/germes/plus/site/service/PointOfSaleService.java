package ru.germes.plus.site.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.repository.PointOfSaleRepository;

import java.util.List;

@Service
public class PointOfSaleService {

    private static final Log log = LogFactory.getLog(PointOfSaleService.class);
    private PointOfSaleRepository pointOfSaleRepository;

    private PointOfSaleService(PointOfSaleRepository pointOfSaleRepository) {
        this.pointOfSaleRepository = pointOfSaleRepository;
    }

    public PointOfSale getById(Long id) {
        log.info("Получение точки по " + id);
        return pointOfSaleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Точка с таким id не найдена"));
    }

    public List<PointOfSale> getAll() {
        log.info("Получение списка всех точек");
        return pointOfSaleRepository.findAll();
    }
}
