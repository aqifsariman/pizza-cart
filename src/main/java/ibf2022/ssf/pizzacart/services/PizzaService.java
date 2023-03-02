package ibf2022.ssf.pizzacart.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.ssf.pizzacart.models.Pizza;
import ibf2022.ssf.pizzacart.repositories.PizzaRepo;
import jakarta.json.JsonObject;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepo pizzaRepo;

    public void saveCart(Pizza pizza) {
        pizzaRepo.saveCart(pizza);
    }

    public Pizza getCart() {
        return pizzaRepo.getCart();
    }

    public void placeOrder(JsonObject contact) {
        pizzaRepo.placeOrder(contact);
    }

    public Optional<Object> getOrder(String orderId){
        return pizzaRepo.findById(orderId);
    }

}
