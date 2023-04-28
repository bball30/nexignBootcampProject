package ru.bootcamp.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbonentNewDto {

    private String numberPhone;

    private String tariff_id;

    private String balance;

}
