package ru.bootcamp.brt.model.dto;

import lombok.Data;
import ru.bootcamp.brt.model.CallDetails;

import java.util.List;

@Data
public class ReportDto {
    private final long id;
    private final String numberPhone;
    private final String tariffIndex;
    private final List<CallDetails> payload;
    private final double totalCost;
    private final String monetaryUnit;
}
