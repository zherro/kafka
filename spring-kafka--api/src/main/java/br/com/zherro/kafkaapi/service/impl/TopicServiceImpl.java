package br.com.zherro.kafkaapi.service.impl;

import br.com.zherro.kafkaapi.model.Topic;
import br.com.zherro.kafkaapi.service.TopicService;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    ConsumerFactory<String,Object> consumerFactory;

    @Override
    public Set<Topic> getTopics() {
        try (Consumer<String, Object> consumer = consumerFactory.createConsumer()) {
            Map<String, List<PartitionInfo>> map = consumer.listTopics();

            Set<Topic> topics = new HashSet<>();
            map.forEach((k, v) -> {
                topics.add(new Topic(k, v.size()));
            });

            return topics;
        }
    }
}
