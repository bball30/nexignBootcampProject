package ru.bootcamp.brt.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CallDetails {
    private String callType;
    private String startTime;
    private String endTime;
    private String duration;
    private Double cost;
}
