package ibf2022.ssf.pizzacart.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.ssf.pizzacart.services.PizzaService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
@RequestMapping(path = "/order")
public class PizzaRestController {

    @Autowired
    PizzaService pizzaSvc;

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable String id) {
        Optional<Object> contact = pizzaSvc.getOrder(id);
        if (contact.isEmpty()) {
            JsonObject resp = Json.createObjectBuilder()
                    .add("message", "Order %s not found.".formatted(id))
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(resp.toString());
        }
        return new ResponseEntity<Object>(contact, HttpStatus.OK);
    }
}
