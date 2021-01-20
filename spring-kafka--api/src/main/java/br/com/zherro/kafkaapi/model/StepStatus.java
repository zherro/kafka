package br.com.zherro.kafkaapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize
public class StepStatus {
    private String uuid;
    private String step;
    private String status;
}
