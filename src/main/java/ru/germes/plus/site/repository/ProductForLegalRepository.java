package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;

import java.util.List;

public interface ProductForLegalRepository extends JpaRepository<ProductForLegal, Long> {

  @Query("SELECT p FROM ProductForLegal p WHERE " +
          "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
          "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
          "(:configurations IS NULL OR p.type IN :configurations)")
  List<ProductForLegal> findWithFilters(
          @Param("minPrice") Integer minPrice,
          @Param("maxPrice") Integer maxPrice,
          @Param("configurations") List<String> configurations);

  List<ProductForLegal> findAllByOrderByPriceAsc();

  List<ProductForLegal> findAllByOrderByPriceDesc();

  List<ProductForLegal> findByNameContainingIgnoreCase(String search);
}