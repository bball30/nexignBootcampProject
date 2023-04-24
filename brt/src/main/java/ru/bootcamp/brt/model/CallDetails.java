package ru.bootcamp.brt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CallDetails {
    private String callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Duration duration;
    private Double cost;
}
