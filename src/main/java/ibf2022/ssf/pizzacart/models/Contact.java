package ibf2022.ssf.pizzacart.models;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Contact implements Serializable {
    @NotEmpty(message = "Field cannot be empty. Please fill in name.")
    private String name;

    @NotEmpty(message = "Field cannot be empty. Please fill in address.")
    private String address;

    @NotEmpty(message = "Field cannot be empty. Please fill in phone number.")
    @Size(min = 8, max = 8)
    private String phone;

    private Boolean rush;

    private String comments;

    private String orderId;

    private float pizzaCost;

    private float total;

    public Contact(@NotEmpty(message = "Field cannot be empty. Please fill in name.") String name,
            @NotEmpty(message = "Field cannot be empty. Please fill in address.") String address,
            @NotEmpty(message = "Field cannot be empty. Please fill in phone number.") @Size(min = 8, max = 8) String phone,
            Boolean rush, String comments, String orderId, float pizzaCost) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rush = rush;
        this.comments = comments;
        this.orderId = orderId;
        this.pizzaCost = pizzaCost;
        this.total = setTotal(rush);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getRush() {
        return rush;
    }

    public void setRush(Boolean rush) {
        this.rush = rush;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    public void setPizzaCost(float pizzaCost) {
        this.pizzaCost = pizzaCost;
    }

    public float getTotal() {
        return total;
    }

    public float setTotal(Boolean rush) {
        if (rush == true) {
            return this.total = getPizzaCost() + 2;
        }
        return this.total = getPizzaCost();
    }

    @Override
    public String toString() {
        return "Contact [name=" + name + ", address=" + address + ", phone=" + phone + ", rush=" + rush + ", comments="
                + comments + ", orderId=" + orderId + ", pizzaCost=" + pizzaCost + ", total=" + total + "]";
    }

    public JsonObject toJson(Pizza pizza) {
        return Json.createObjectBuilder()
                .add("orderId", this.orderId)
                .add("name", this.name)
                .add("address", this.address)
                .add("phone", this.phone)
                .add("rush", this.rush)
                .add("comments", this.comments)
                .add("pizza", pizza.getPizza())
                .add("size", pizza.getSize())
                .add("quantity", pizza.getQuantity())
                .add("total", this.total)
                .build();
    }
}
