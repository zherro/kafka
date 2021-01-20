package br.com.zherro.kafkaapi.controller;

import br.com.zherro.kafkaapi.model.dto.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrderController {

    public ResponseEntity sendOrder(@RequestBody OrderDTO order);
}