package br.com.zherro.kafkaapi.model.dto;


import br.com.zherro.kafkaapi.config.modelmapper.UtilDTO;
import br.com.zherro.kafkaapi.model.Order;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDTO extends UtilDTO<OrderDTO, Order> {
    private BigDecimal price;
    private String description;
    private String creditCardNumber;
}
