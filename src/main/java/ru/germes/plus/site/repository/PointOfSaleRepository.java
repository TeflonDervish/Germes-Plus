package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.germes.plus.site.model.PointOfSale;

public interface PointOfSaleRepository extends JpaRepository<PointOfSale, Long> {
}
