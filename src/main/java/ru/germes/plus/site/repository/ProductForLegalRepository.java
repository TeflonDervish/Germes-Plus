package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;

import java.util.List;

public interface ProductForLegalRepository extends JpaRepository<ProductForLegal, Long> {
  List<ProductForLegal> findAllByOrderByPriceAsc();

  List<ProductForLegal> findAllByOrderByPriceDesc();

  List<ProductForLegal> findByNameContainingIgnoreCase(String search);
}