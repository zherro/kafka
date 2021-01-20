package br.com.zherro.kafkaapi.service;

import br.com.zherro.kafkaapi.model.Topic;

import java.util.Set;

public interface TopicService {
    Set<Topic> getTopics();
}
