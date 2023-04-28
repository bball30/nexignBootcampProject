package ru.bootcamp.hrs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CallDetails {
    private String callType;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String tariffIndex;
    private Long fixedPrice;
    private Long fixedIncludedMinutes;
    private Float includedPriceForMinute;
    private Float priceForMinute;
    private Boolean incomingPaid;
    private Boolean outgoingPaid;
    private Boolean insideOperatorPaid;
    private Boolean insideOperatorCall;
}
