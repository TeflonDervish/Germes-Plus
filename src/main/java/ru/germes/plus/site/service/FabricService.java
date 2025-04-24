package ru.germes.plus.site.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.Fabric;
import ru.germes.plus.site.repository.FabricRepository;

import java.util.List;

@Service
public class FabricService {

    private static final Log log = LogFactory.getLog(FabricService.class);
    private FabricRepository fabricRepository;

    public FabricService(FabricRepository fabricRepository) {
        this.fabricRepository = fabricRepository;
    }

    public Fabric getFabric() {
        log.info("Получение информации о фабрике");
        return fabricRepository.findAll().get(0);
    }
}
