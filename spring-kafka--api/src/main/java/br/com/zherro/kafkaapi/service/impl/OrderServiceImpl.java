package br.com.zherro.kafkaapi.service.impl;

import br.com.zherro.kafkaapi.model.Order;
import br.com.zherro.kafkaapi.model.StepStatus;
import br.com.zherro.kafkaapi.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OrderServiceImpl implements OrderService {

    private static final String TOPIC = "TESTE_SERVICE";
    private static final Logger logger = LoggerFactory.getLogger(Order.class);

    @Value("${spring.kafka.producer.order.create}")
    private String TOPIC_SEND_ORDER;

    @Autowired
    KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public Order sendOrder(Order order) {
        // define serializador
        this.kafkaTemplate.setMessageConverter(new JsonMessageConverter());

        logger.info(String.format("#### -> Producing message -> %s", order));

        // envia para o broker
        this.kafkaTemplate.send(TOPIC_SEND_ORDER, order.getUuid(), order);

        return order;
    }

    @Override
    @KafkaListener( topics = "${spring.kafka.consumer.order.status}") //,  groupId = "ORDER_API")
    public void consume(@Payload StepStatus status, @Headers MessageHeaders headers) throws IOException {

//
//        if(order.isPresent()){
//            logger.info(String.format("#### -> Consumed message -> %s",order));
//            order.get().getStatusList().add(
//                    status.getStep()
//                            .concat(":")
//                            .concat(status.getStatus())
//            );
//            orderRepository.save(order.get());
//        }
    }
}
