package ibf2022.ssf.pizzacart.repositories;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.ssf.pizzacart.models.Pizza;
import jakarta.json.JsonObject;

@Repository
public class PizzaRepo {
    @Autowired
    @Qualifier("my-redis")
    RedisTemplate<String, String> redisTemplate;
    private Logger logger = Logger.getLogger(PizzaRepo.class.getName());

    public void saveCart(final Pizza pizza) {
        redisTemplate.opsForHash().put("customerCart", "temporary", pizza);
    }

    public Pizza getCart() {
        return (Pizza) redisTemplate.opsForHash().get("customerCart", "temporary");
    }

    public void placeOrder(JsonObject contact) {
        logger.log(Level.INFO, "Contact Details: %s".formatted(contact.toString()));
        redisTemplate.opsForHash().put("orders", contact.getString("orderId"), contact.toString());
    }

    public Optional<Object> findById(String orderId) {
        logger.log(Level.INFO,
                "Details from JSON String: %s".formatted(redisTemplate.opsForHash().get("orders", orderId)));
        Object obj = redisTemplate.opsForHash().get("orders", orderId);
        if ((obj == null) || (obj.toString().length() <= 0)) {
            return Optional.empty();
        }
        return Optional.of(redisTemplate.opsForHash().get("orders", orderId));
    }
}
