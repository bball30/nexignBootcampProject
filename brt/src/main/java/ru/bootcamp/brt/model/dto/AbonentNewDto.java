package ru.bootcamp.brt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bootcamp.brt.model.Tariff;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbonentNewDto {

    private String telNumber;

    private String tariff;

    private String balance;

}
