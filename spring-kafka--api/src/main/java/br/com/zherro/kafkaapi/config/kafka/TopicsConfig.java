package br.com.zherro.kafkaapi.config.kafka;

import net.bytebuddy.TypeCache;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.Arrays;

@Configuration
public class TopicsConfig {
    @Value("${spring.kafka.partitions}")
    private int partitions;

    @Value("${spring.kafka.replica}")
    private int replica;

    @Bean
    public NewTopic topic6() {
        return TopicBuilder.name("ORDER_STATUS_SERVICE")
                .partitions(partitions)
                .replicas(replica)
                .build();
    }
}
