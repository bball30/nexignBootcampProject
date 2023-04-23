package ru.bootcamp.brt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AbonentResponseNewDto {

    private String id;

    private String numberPhone;

    private String balance;

}
