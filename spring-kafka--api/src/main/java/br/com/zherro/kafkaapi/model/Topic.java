package br.com.zherro.kafkaapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class Topic {
    private String topic;
    private Integer partitions;
}
