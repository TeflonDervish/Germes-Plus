package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.germes.plus.site.model.Fabric;

@Repository
public interface FabricRepository extends JpaRepository<Fabric, Long> {
}
