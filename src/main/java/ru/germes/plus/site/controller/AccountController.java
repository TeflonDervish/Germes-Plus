package ru.germes.plus.site.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.germes.plus.site.enums.OrderStatus;
import ru.germes.plus.site.model.orders.OrderForIndividual;
import ru.germes.plus.site.model.orders.OrderForLegal;
import ru.germes.plus.site.model.persons.IndividualPerson;
import ru.germes.plus.site.model.persons.LegalPerson;
import ru.germes.plus.site.service.OrderForIndividualService;
import ru.germes.plus.site.service.OrderForLegalService;
import ru.germes.plus.site.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private static final Log log = LogFactory.getLog(AccountController.class);

    private final UserService userService;
    private final OrderForIndividualService orderForIndividualService;
    private final OrderForLegalService orderForLegalService;

//    @ModelAttribute
//    public void addAttributes(Model model,
//                              @AuthenticationPrincipal UserDetails user) {
//        if (user instanceof IndividualPerson individualPerson) {
//            model.addAttribute("account", individualPerson);
//        } else if (user instanceof LegalPerson legalPerson) {
//            model.addAttribute("account", legalPerson);
//        }
//    }


    @GetMapping
    public String getAccount(@AuthenticationPrincipal UserDetails user,
                             Model model) {
        if (user instanceof IndividualPerson individualPerson) {
            individualPerson = userService.getIndividualById(individualPerson.getId());
            List<OrderForIndividual> orders = orderForIndividualService.getOrderForIndividual(individualPerson);
            model.addAttribute("account", individualPerson);
            model.addAttribute("orders", orders.stream()
                    .filter(x -> !x.getStatus().equals(OrderStatus.COMPLETED)));
            model.addAttribute("ordersComplete", orders.stream()
                    .filter(x -> x.getStatus().equals(OrderStatus.COMPLETED)));
            return "myAccount";
        } else if (user instanceof LegalPerson legalPerson) {
            legalPerson = userService.getLegalById(legalPerson.getId());
            List<OrderForLegal> orders = orderForLegalService.getOrderForLegal(legalPerson);
            model.addAttribute("account", legalPerson);
            model.addAttribute("orders", orders.stream()
                    .filter(x -> !x.getStatus().equals(OrderStatus.COMPLETED)));
            model.addAttribute("ordersComplete", orders.stream()
                    .filter(x -> x.getStatus().equals(OrderStatus.COMPLETED)));
            return "forLegalPerson/myAccount";
        } else {
            return "redirect:/";
        }

    }

    @PostMapping("/edit")
    public String edit(@AuthenticationPrincipal IndividualPerson user,
                       @ModelAttribute IndividualPerson individualPerson) {
        userService.save(user.getId(), individualPerson);
        return "redirect:/account";
    }

    @PostMapping("/edit/legal")
    public String edit(@AuthenticationPrincipal LegalPerson user,
                       @ModelAttribute LegalPerson legalPerson) {
        userService.save(user.getId(), legalPerson);
        return "redirect:/account";
    }
}
