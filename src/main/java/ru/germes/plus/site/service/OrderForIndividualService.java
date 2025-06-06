package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.germes.plus.site.dto.DeliveryDetailsDto;
import ru.germes.plus.site.dto.OrderForIndividualDto;
import ru.germes.plus.site.dto.PickupDetailsDto;
import ru.germes.plus.site.enums.DeliveryType;
import ru.germes.plus.site.enums.OrderStatus;
import ru.germes.plus.site.model.korzina.KorzinaForIndividual;
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
    private final KorzinaForIndividualService korzinaForIndividualService;
    private final PointOfSaleService pointOfSaleService;

    @Transactional
    public OrderForIndividual createOrder(IndividualPerson user, OrderForIndividualDto orderForIndividualDto) {
        log.info("Создание заказа для " + user.getEmail());

        KorzinaForIndividual korzinaForIndividual = korzinaForIndividualService.getKorzina(user);

        OrderForIndividual order = new OrderForIndividual();
        order.setIndividualPerson(user);
        order.setProducts(new ArrayList<>(korzinaForIndividual.getProducts()));
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.WAITING_ACCESS);

        Integer totalPrice = 0;
        for (ProductForIndividual product : korzinaForIndividual.getProducts())
            totalPrice += product.getPrice();

        order.setTotalPrice(totalPrice);
        order.setOrderDate(LocalDate.now());

        if (orderForIndividualDto.getDeliveryType().equals("delivery")) {
            processDelivery(order, orderForIndividualDto.getDeliveryDetails());
        } else {
            processPickup(order, orderForIndividualDto.getPickupDetails());
        }


        OrderForIndividual savedOrder = orderForIndividualRepository.save(order);

        korzinaForIndividualService.clear(user);

        return savedOrder;
    }

    private void processDelivery(OrderForIndividual order, DeliveryDetailsDto details) {
        log.info("Заказ с доставкой");
        order.setDeliveryPrice(500);
        order.setDeliveryType(DeliveryType.DELIVERY);
        order.setDeliveryAddress(details.getAddress());
    }

    private void processPickup(OrderForIndividual order, PickupDetailsDto details) {
        log.info("Самовывоз");
        order.setDeliveryType(DeliveryType.PICKUP);

        order.setDeliveryPrice(0);
        PointOfSale pointOfSale = pointOfSaleService.getById(details.getPickupPointId());

        order.setPointOfSale(pointOfSale);
    }

    public List<OrderForIndividual> getOrderForIndividual(IndividualPerson user) {
        log.info("Получение заказов пользователя");
        return orderForIndividualRepository.findByIndividualPersonId(user.getId());
    }
}
