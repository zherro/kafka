package br.com.zherro.kafkaapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Order {

    private String uuid;
    private BigDecimal price;
    private String description;
    private String creditCardNumber;
    private LocalDateTime createdAt;

    @JsonIgnore
    private List<String> statusList = new ArrayList<>();

    public Order(){
        this.uuid = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String toString(){
        return "Order::: [ uuid: " + this.uuid + ", price: " + this.description + ", description: " + this.price + " ]";
    }
}
