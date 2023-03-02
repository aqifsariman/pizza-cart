package ibf2022.ssf.pizzacart.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ibf2022.ssf.pizzacart.models.Contact;
import ibf2022.ssf.pizzacart.models.Pizza;
import ibf2022.ssf.pizzacart.services.PizzaService;

@Controller
@RequestMapping(path = "/pizza")
public class PizzaController {

    @Autowired
    private PizzaService pizzaSvc;
    private Logger logger = Logger.getLogger(PizzaController.class.getName());

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String postPizza(@RequestBody MultiValueMap<String, String> form) {

        String pizzaSelected = form.getFirst("pizza");
        String size = form.getFirst("size");
        Integer quantity = Integer.parseInt(form.getFirst("quantity"));
        Pizza cart = new Pizza(pizzaSelected, size, quantity);
        pizzaSvc.saveCart(cart);
        return "contact";
    }

    @PostMapping(path = "/order", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String placeOrder(Contact contact, BindingResult br,
            @RequestBody MultiValueMap<String, String> form,
            Model model) {

        // ! DATA FROM FORM
        String name = form.getFirst("name");
        String address = form.getFirst("address");
        String phone = form.getFirst("phone");
        String comments = form.getFirst("comments");
        Boolean rush = false;
        if (form.getFirst("rush") != null) {
            rush = Boolean.parseBoolean(form.getFirst("rush"));
        }

        // ! DATA FROM PIZZA CART
        Pizza pizza = pizzaSvc.getCart();
        String orderId = pizza.getOrderId();
        float pizzaCost = pizza.getPizzaCost();

        contact = new Contact(name, address, phone, rush, comments, orderId, pizzaCost);
        logger.log(Level.INFO, "Contact Details: %s".formatted(contact.toString()));

        pizzaSvc.placeOrder(contact.toJson(pizza));
        model.addAttribute("confirmation", contact);

        return "order-confirmation";

    }

}