package ru.bootcamp.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbonentResponseTariffDto {
    private String id;

    private String numberPhone;

    private String tariff_id;
}
