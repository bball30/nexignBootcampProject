package ru.bootcamp.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CallDetailsDto {
    private final String callType;
    private final String startTime;
    private final String endTime;
    private final String duration;
    private final double cost;
}
