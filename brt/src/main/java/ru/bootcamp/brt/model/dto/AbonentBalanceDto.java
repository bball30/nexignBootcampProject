package ru.bootcamp.brt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AbonentBalanceDto {
    private final String numberPhone;
    private final double balance;
}
