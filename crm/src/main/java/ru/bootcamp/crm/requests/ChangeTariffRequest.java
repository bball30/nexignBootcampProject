package ru.bootcamp.crm.requests;

import lombok.Data;

@Data
public class ChangeTariffRequest {
    private final String numberPhone;
    private final String tariff_id;
}
