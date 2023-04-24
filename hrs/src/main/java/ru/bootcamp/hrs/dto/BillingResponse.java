package ru.bootcamp.hrs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class BillingResponse {
    private String numberPhone;
    private String callType;
    private String tariffIndex;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long duration;
    private double cost;
}
