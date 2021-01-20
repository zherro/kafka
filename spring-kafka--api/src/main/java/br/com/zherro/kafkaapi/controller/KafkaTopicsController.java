package br.com.zherro.kafkaapi.controller;

import org.springframework.http.ResponseEntity;

public interface KafkaTopicsController {
    ResponseEntity listTopics();
}
