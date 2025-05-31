package ru.germes.plus.site.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.germes.plus.site.dto.DeliveryDetailsDto;
import ru.germes.plus.site.dto.OrderDto;
import ru.germes.plus.site.dto.PickupDetailsDto;
import ru.germes.plus.site.model.korzina.KorzinaForIndividual;
import ru.germes.plus.site.model.orders.OrderForIndividual;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.service.KorzinaService;
import ru.germes.plus.site.service.OrderForIndividualService;
import ru.germes.plus.site.service.PointOfSaleService;

@Controller
@RequestMapping("/korzina")
@AllArgsConstructor
public class KorzinaController {

    private static final Log log = LogFactory.getLog(KorzinaController.class);
    private final KorzinaService korzinaService;
    private final OrderForIndividualService orderForIndividualService;
    private final PointOfSaleService pointOfSaleService;

    @GetMapping
    public String getKorzina(Model model,
                             @AuthenticationPrincipal IndividualPerson individualPerson) {

        KorzinaForIndividual korzinaForIndividual = korzinaService.getKorzina(individualPerson);

        double totalPrice = 0;
        for (ProductForIndividual product : korzinaForIndividual.getProducts())
            totalPrice += product.getPrice();

        model.addAttribute("products", korzinaForIndividual.getProducts());

        model.addAttribute("total_price", totalPrice);

        model.addAttribute("product_count", korzinaForIndividual.getProducts().size());

        model.addAttribute("phone_number", individualPerson.getPhone());
        model.addAttribute("surname_name", individualPerson.getSurname() + " " + individualPerson.getName());

        model.addAttribute("points", pointOfSaleService.getAll());


        return "korzina";
    }

    @PostMapping("/{id}/add_to_korzina")
    public String addToKorzina(@AuthenticationPrincipal IndividualPerson individualPerson,
                               @PathVariable Long id) {
        korzinaService.addProduct(id, individualPerson);

        return "redirect:/korzina";
    }

    @PostMapping("/{id}/delete_from_korzina")
    public String removeFromKorzina(@AuthenticationPrincipal IndividualPerson individualPerson,
                                    @PathVariable Long id) {
        korzinaService.deleteProduct(id, individualPerson);
        return "redirect:/korzina";
    }

    @PostMapping("/make-order")
    public String processOrder(@ModelAttribute OrderDto orderDto,
                               @ModelAttribute DeliveryDetailsDto deliveryDetailsDto,
                               @ModelAttribute PickupDetailsDto pickupDetailsDto,
                               @AuthenticationPrincipal IndividualPerson individualPerson,
                               RedirectAttributes redirectAttributes) {
        orderDto.setDeliveryDetails(deliveryDetailsDto);
        orderDto.setPickupDetails(pickupDetailsDto);
        try {
            OrderForIndividual order = orderForIndividualService.createOrder(individualPerson, orderDto);

            redirectAttributes.addFlashAttribute("success",
                    "Заказ №" + order.getId() + " успешно оформлен!");
            return "redirect:/account";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/korzina";
        }
    }

}
