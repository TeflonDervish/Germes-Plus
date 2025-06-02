package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.germes.plus.site.model.orders.OrderForIndividual;
import ru.germes.plus.site.model.orders.OrderForLegal;

import java.util.List;

@Repository
public interface OrderForLegalRepository extends JpaRepository<OrderForLegal, Long> {

    List<OrderForLegal> findByLegalPersonId(Long id);
}
