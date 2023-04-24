package ru.bootcamp.crm.requests;

import lombok.Data;

@Data
public class PayRequest {
    private final String numberPhone;
    private final double money;
}
