package ru.bootcamp.crm.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class BillingDto {
    private final List<AbonentBalanceDto> numbers;

    @JsonCreator
    public BillingDto(@JsonProperty("numbers") List<AbonentBalanceDto> numbers) {
        this.numbers = numbers;
    }
}
