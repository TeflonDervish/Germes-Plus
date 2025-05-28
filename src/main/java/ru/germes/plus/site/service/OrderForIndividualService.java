package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.germes.plus.site.dto.DeliveryDetailsDto;
import ru.germes.plus.site.dto.OrderDto;
import ru.germes.plus.site.dto.PickupDetailsDto;
import ru.germes.plus.site.enums.DeliveryType;
import ru.germes.plus.site.enums.OrderStatus;
import ru.germes.plus.site.model.Korzina;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.orders.OrderForIndividual;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.repository.OrderForIndividualRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderForIndividualService {

    private static final Log log = LogFactory.getLog(OrderForIndividualService.class);

    private final OrderForIndividualRepository orderForIndividualRepository;
    private final KorzinaService korzinaService;
    private final PointOfSaleService pointOfSaleService;

    @Transactional
    public OrderForIndividual createOrder(IndividualPerson user, OrderDto orderDto) {
        log.info("Создание заказа для " + user.getEmail());

        Korzina korzina = korzinaService.getKorzina(user);

        OrderForIndividual order = new OrderForIndividual();
        order.setIndividualPerson(user);
        order.setProducts(new ArrayList<>(korzina.getProducts()));
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.WAITING_ACCESS);

        Integer totalPrice = 0;
        for (ProductForIndividual product : korzina.getProducts())
            totalPrice += product.getPrice();

        order.setTotalPrice(totalPrice);
        order.setOrderDate(LocalDate.now());

        if (orderDto.getDeliveryType().equals("delivery")) {
            processDelivery(order, orderDto.getDeliveryDetails());
        } else {
            processPickup(order, orderDto.getPickupDetails());
        }

        OrderForIndividual savedOrder = orderForIndividualRepository.save(order);

        korzinaService.clear(user);

        return savedOrder;
    }

    private void processDelivery(OrderForIndividual order, DeliveryDetailsDto details) {
        log.info("Заказ с доставкой");
        order.setDeliveryType(DeliveryType.DELIVERY);
        order.setDeliveryAddress(details.getAddress());
    }

    private void processPickup(OrderForIndividual order, PickupDetailsDto details) {
        log.info("Самовывоз");
        order.setDeliveryType(DeliveryType.PICKUP);

        PointOfSale pointOfSale = pointOfSaleService.getById(details.getPickupPointId());

        order.setPointOfSale(pointOfSale);
    }

    public List<OrderForIndividual> getOrderForIndividual(IndividualPerson user) {
        log.info("Получение заказов пользователя");
        return orderForIndividualRepository.findByIndividualPersonId(user.getId());
    }
}
