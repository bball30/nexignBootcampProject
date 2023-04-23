package ru.bootcamp.brt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bootcamp.brt.model.Abonent;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbonentNewDto {

    private String numberPhone;

    private String tariff_id;

    private String balance;

    public AbonentNewDto(Abonent abonent) {
        this.numberPhone = abonent.getTelNumber();
        this.tariff_id = abonent.getTariff().getTariffId();
        this.balance = abonent.getBalance().toString();
    }
}
