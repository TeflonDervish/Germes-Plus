package ru.germes.plus.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.germes.plus.site.model.orders.OrderForIndividual;

@Repository
public interface OrderForIndividualRepository extends JpaRepository<OrderForIndividual, Long> {
}
