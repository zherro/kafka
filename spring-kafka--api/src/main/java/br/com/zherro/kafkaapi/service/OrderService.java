package br.com.zherro.kafkaapi.service;

import br.com.zherro.kafkaapi.model.Order;
import br.com.zherro.kafkaapi.model.StepStatus;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;

public interface OrderService {
    Order sendOrder(Order order);
    void consume(@Payload StepStatus status, @Headers MessageHeaders headers) throws IOException;
}