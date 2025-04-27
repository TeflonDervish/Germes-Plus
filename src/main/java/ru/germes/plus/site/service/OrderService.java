package ru.germes.plus.site.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.germes.plus.site.enums.OrderStatus;
import ru.germes.plus.site.model.Korzina;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.ShippingInformation;
import ru.germes.plus.site.model.orders.OrderForIndividual;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.repository.OrderForIndividualRepository;

@Service
public class OrderService {

    @Autowired
    private OrderForIndividualRepository orderForIndividualRepository;

    @Autowired
    private KorzinaService korzinaService;

    public OrderForIndividual makeOrder(IndividualPerson individualPerson, PointOfSale pointOfSale, ShippingInformation shippingInformation) {
        Korzina korzina = korzinaService.getKorzina(individualPerson);

        OrderForIndividual order = new OrderForIndividual();
        order.setStatus(OrderStatus.WAITING_ACCESS);
        order.setProducts(korzina.getProducts());
        order.setIndividualPerson(individualPerson);

        if (pointOfSale == null && shippingInformation != null) {
            order.setShippingInformation(shippingInformation);
        }else if (pointOfSale != null && shippingInformation == null) {
            order.setPointOfSale(pointOfSale);
        }

        return orderForIndividualRepository.save(order);

    }
}
