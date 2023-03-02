package ibf2022.ssf.pizzacart.models;

import java.io.Serializable;
import java.util.Random;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Pizza implements Serializable {
    @NotNull(message = "Please make a selection.")
    private String pizza;

    private String size;

    @Min(value = 1, message = "Please order a minimum of 1 pizza.")
    @Max(value = 10, message = "Please order a maximum of 10 pizza.")
    private Integer quantity;

    private String orderId;

    private float pizzaCost;

    public Pizza() {
    }

    public Pizza(@NotEmpty String pizza, @NotEmpty String size,
            @NotEmpty @Size(min = 1, max = 10, message = "Please order a minimum of 1 pizza and a maximum of 10 pizzas.") Integer quantity) {
        this.orderId = generateId(8);
        this.pizza = pizza;
        this.size = size;
        this.quantity = quantity;
        setPizzaCost(pizza, size, quantity);
    }

    // SYNCHRONIZED BLOCKS IT
    // THIS CREATES UNIQUE ID TO PREVENT DUPLICATES
    private synchronized String generateId(int numOfChars) {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();

        while (sb.length() < numOfChars) {
            sb.append(Integer.toHexString(rand.nextInt()));
        }
        return sb.toString().substring(0, numOfChars).toUpperCase();
    }

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public float getPizzaCost() {
        return pizzaCost;
    }

    public void setPizzaCost(String pizza, String size, Integer quantity) {
        if (pizza.equals("marinara") || pizza.equals("bella") || pizza.equals("spiniatacalbrese")) {
            if (size.equals("md")) {
                this.pizzaCost = (float) (quantity * 30 * 1.2);
            } else if (size.equals("lg")) {
                this.pizzaCost = (float) (quantity * 30 * 1.5);
            } else {
                this.pizzaCost = (float) (quantity * 30);
            }
        } else if (pizza.equals("margherita")) {
            if (size.equals("md")) {
                this.pizzaCost = (float) (quantity * 22 * 1.2);
            } else if (size.equals("lg")) {
                this.pizzaCost = (float) (quantity * 22 * 1.5);
            } else {
                this.pizzaCost = (float) (quantity * 22);
            }
        } else if (pizza.equals("trioformaggio")) {
            if (size.equals("md")) {
                this.pizzaCost = (float) (quantity * 25 * 1.2);
            } else if (size.equals("lg")) {
                this.pizzaCost = (float) (quantity * 25 * 1.5);
            } else {
                this.pizzaCost = (float) (quantity * 25);
            }
        }
    }

}
