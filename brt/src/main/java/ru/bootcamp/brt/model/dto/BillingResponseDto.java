package ru.bootcamp.brt.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class BillingResponseDto {
    private final List<AbonentBalanceDto> numbers;

    @JsonCreator
    public BillingResponseDto(@JsonProperty("numbers") List<AbonentBalanceDto> numbers) {
        this.numbers = numbers;
    }
}
