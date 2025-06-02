package ru.germes.plus.site.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.germes.plus.site.dto.DeliveryDetailsDto;
import ru.germes.plus.site.dto.OrderForIndividualDto;
import ru.germes.plus.site.dto.OrderForLegalDto;
import ru.germes.plus.site.dto.PickupDetailsDto;
import ru.germes.plus.site.model.korzina.KorzinaForIndividual;
import ru.germes.plus.site.model.korzina.KorzinaForLegal;
import ru.germes.plus.site.model.orders.OrderForIndividual;
import ru.germes.plus.site.model.orders.OrderForLegal;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.model.products.ProductForIndividual;
import ru.germes.plus.site.model.products.ProductForLegal;
import ru.germes.plus.site.service.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/korzina")
@AllArgsConstructor
public class KorzinaController {

    private static final Log log = LogFactory.getLog(KorzinaController.class);
    private final KorzinaForIndividualService korzinaForIndividualService;
    private final KorzinaForLegalService korzinaForLegalService;
    private final OrderForIndividualService orderForIndividualService;
    private final OrderForLegalService orderForLegalService;
    private final PointOfSaleService pointOfSaleService;
    private final FabricService fabricService;

    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("points", pointOfSaleService.getAll());
        model.addAttribute("fabrics", fabricService.getAll());
    }

    @GetMapping
    public String getKorzina(Model model,
                             @AuthenticationPrincipal UserDetails user) {
        if (user instanceof IndividualPerson individualPerson) {
            KorzinaForIndividual korzina = korzinaForIndividualService.getKorzina(individualPerson);

            double totalPrice = 0;
            for (ProductForIndividual product : korzina.getProducts())
                totalPrice += product.getPrice();

            model.addAttribute("products", korzina.getProducts());
            model.addAttribute("total_price", totalPrice);
            model.addAttribute("product_count", korzina.getProducts().size());
            model.addAttribute("phone_number", individualPerson.getPhone());
            model.addAttribute("surname_name", individualPerson.getSurname() + " " + individualPerson.getName());

            return "korzina";
        } else if (user instanceof LegalPerson legalPerson) {
            KorzinaForLegal korzina = korzinaForLegalService.getKorzina(legalPerson);

            double totalPrice = 0;
            for (ProductForLegal product : korzina.getProducts())
                totalPrice += product.getPrice();

            model.addAttribute("products", korzina.getProducts());
            model.addAttribute("total_price", totalPrice);
            model.addAttribute("product_count", korzina.getProducts().size());

            return "forLegalPerson/korzina";
        }
        return "redirect:/login";
    }

    @PostMapping("/{id}/add_to_korzina")
    public String addToKorzina(@AuthenticationPrincipal UserDetails user,
                               @PathVariable Long id) {

        if (user instanceof IndividualPerson individualPerson) {
            korzinaForIndividualService.addProduct(id, individualPerson);
        } else if (user instanceof LegalPerson legalPerson) {
            korzinaForLegalService.addProduct(id, legalPerson);
        }

        return "redirect:/korzina";
    }

    @PostMapping("/{id}/delete_from_korzina")
    public String removeFromKorzina(@AuthenticationPrincipal UserDetails user,
                                    @PathVariable Long id) {

        if (user instanceof IndividualPerson individualPerson) {
            korzinaForIndividualService.deleteProduct(id, individualPerson);
        } else if (user instanceof LegalPerson legalPerson) {
            korzinaForLegalService.deleteProduct(id, legalPerson);
        }
        return "redirect:/korzina";
    }

    @PostMapping("/make-order")
    public String processOrder(@ModelAttribute OrderForIndividualDto orderForIndividualDto,
                               @ModelAttribute DeliveryDetailsDto deliveryDetailsDto,
                               @ModelAttribute PickupDetailsDto pickupDetailsDto,
                               @AuthenticationPrincipal IndividualPerson individualPerson,
                               RedirectAttributes redirectAttributes) {
        orderForIndividualDto.setDeliveryDetails(deliveryDetailsDto);
        orderForIndividualDto.setPickupDetails(pickupDetailsDto);
        try {
            OrderForIndividual order = orderForIndividualService.createOrder(individualPerson, orderForIndividualDto);

            redirectAttributes.addFlashAttribute("success",
                    "Заказ №" + order.getId() + " успешно оформлен!");
            return "redirect:/account";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/korzina";
        }
    }

    @PostMapping("/make-order/legal")
    public String processOrder(@ModelAttribute OrderForLegalDto orderForLegalDto,
                               @AuthenticationPrincipal LegalPerson legalPerson,
                               RedirectAttributes redirectAttributes) {
        try {
            OrderForLegal order = orderForLegalService.createOrder(legalPerson, orderForLegalDto);

            redirectAttributes.addFlashAttribute("success",
                    "Заказ №" + order.getId() + " успешно оформлен!");
            return "redirect:/account";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/korzina";
        }
    }

}
