package ru.germes.plus.site.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.germes.plus.site.model.PointOfSale;
import ru.germes.plus.site.model.ShippingInformation;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.service.KorzinaService;
import ru.germes.plus.site.service.OrderService;

@RestController
public class OrderController {

    private static final Log log = LogFactory.getLog(OrderController.class);
    @Autowired
    private OrderService orderService;

    @Autowired
    private KorzinaService korzinaService;

    @PostMapping("/make-order")
    public String makeOrder(@AuthenticationPrincipal IndividualPerson individualPerson,
                            @RequestParam String address,
                            @RequestParam String floor) {

        log.info("Сделан заказ");
        korzinaService.clear(individualPerson);
        ShippingInformation shipping = new ShippingInformation();
        shipping.setAddress(address);
        shipping.setFloor(Integer.valueOf(floor));
        orderService.makeOrder(individualPerson, null, shipping);

        return "redirect:/account";

    }

}
