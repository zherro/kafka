package br.com.zherro.kafkaapi.controller.impl;

import br.com.zherro.kafkaapi.model.Order;
import br.com.zherro.kafkaapi.model.dto.OrderDTO;
import br.com.zherro.kafkaapi.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class OrderControllerImpl {
    @Autowired
    private OrderService service;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping(value = "/new-order")
    public ResponseEntity sendOrder(@RequestBody OrderDTO order) {
        Order order1 = this.service.sendOrder(order.toEntity(modelMapper, Order.class));
        return ResponseEntity.ok(order1);
    }
}
