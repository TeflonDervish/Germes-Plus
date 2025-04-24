package ru.germes.plus.site.service;

import org.springframework.stereotype.Service;
import ru.germes.plus.site.model.Fabric;
import ru.germes.plus.site.repository.FabricRepository;

@Service
public class FabricService {

    private FabricRepository fabricRepository;

    public FabricService(FabricRepository fabricRepository) {
        this.fabricRepository = fabricRepository;
    }

    public Fabric getFabric() {
        return fabricRepository.findAll().get(0);
    }
}
