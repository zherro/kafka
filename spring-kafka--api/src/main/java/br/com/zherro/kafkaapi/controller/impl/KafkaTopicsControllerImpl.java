package br.com.zherro.kafkaapi.controller.impl;

import br.com.zherro.kafkaapi.controller.KafkaTopicsController;
import br.com.zherro.kafkaapi.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka/topics")
public class KafkaTopicsControllerImpl  implements KafkaTopicsController {

    @Autowired
    TopicService topicService;

    @Autowired
    private KafkaAdmin admin;

    @Override
    @GetMapping
    public ResponseEntity listTopics() {
        var result = topicService.getTopics();
        return ResponseEntity.ok(result);
    }

}
