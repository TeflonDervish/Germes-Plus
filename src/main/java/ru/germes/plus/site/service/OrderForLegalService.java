package ru.germes.plus.site.service;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.germes.plus.site.dto.DeliveryDetailsDto;
import ru.germes.plus.site.dto.OrderForIndividualDto;
import ru.germes.plus.site.dto.OrderForLegalDto;
import ru.germes.plus.site.dto.PickupDetailsDto;
import ru.germes.plus.site.enums.DeliveryType;
import ru.germes.plus.site.enums.OrderStatus;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.korzina.KorzinaForIndividual;
import ru.germes.plus.site.model.korzina.KorzinaForLegal;
import ru.germes.plus.site.model.orders.OrderForIndividual;
import ru.germes.plus.site.model.orders.OrderForLegal;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;
import ru.germes.plus.site.repository.OrderForLegalRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderForLegalService {

    private static final Log log = LogFactory.getLog(OrderForLegalService.class);

    private final OrderForLegalRepository orderForLegalRepository;
    private final KorzinaForLegalService korzinaForLegalService;
    private final FabricService fabricService;
    @Transactional
    public OrderForLegal createOrder(LegalPerson user, OrderForLegalDto orderForLegalDto) {
        log.info("Создание заказа для " + user.getEmail());

        KorzinaForLegal korzina = korzinaForLegalService.getKorzina(user);

        OrderForLegal order = new OrderForLegal();
        order.setLegalPerson(user);
        order.setProducts(new ArrayList<>(korzina.getProducts()));
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.WAITING_ACCESS);

        Long totalPrice = 0L;
        for (ProductForLegal product : korzina.getProducts())
            totalPrice += product.getPrice();

        order.setTotalPrice(totalPrice);
        order.setOrderDate(LocalDate.now());

        order.setFabric(fabricService.getById(orderForLegalDto.getFabricId()));

        OrderForLegal savedOrder = orderForLegalRepository.save(order);

        korzinaForLegalService.clear(user);

        return savedOrder;
    }

    public List<OrderForLegal> getOrderForLegal(LegalPerson user) {
        log.info("Получение заказов пользователя");
        return orderForLegalRepository.findByLegalPersonId(user.getId());
    }
}
