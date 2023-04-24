package ru.bootcamp.crm.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReportDto {
    private final long id;
    private final String numberPhone;
    private final String tariffIndex;
    private final List<CallDetailsDto> payload;
    private final double totalCost;
    private final String monetaryUnit;
}
