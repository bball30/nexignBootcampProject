package ru.bootcamp.crm.requests;

import lombok.Data;

@Data
public class AbonentNewRequest {
    private final String numberPhone;
    private final String tariff_id;
    private final double balance;
}
