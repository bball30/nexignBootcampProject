package ru.bootcamp.brt.model.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TariffType {
    UNLIMITED("06"),
    MINUTE("03"),
    CLASSIC("11");

    private final String stringTariffType;

    TariffType(String stringTariffType) {
        this.stringTariffType = stringTariffType;
    }
    
    public static TariffType parseTariffType(String inputTariffType) {
        TariffType tariffType;
        switch (inputTariffType) {
            case "06":
                tariffType = UNLIMITED;
                break;
            case "03":
                tariffType = MINUTE;
                break;
            case "11":
                tariffType = CLASSIC;
                break;
            default:
                throw new IllegalStateException("Такого тарифа нет в базе: " + inputTariffType);
        }
        return tariffType;
        
    }

    public static List<String> getStringTypes() {
        return Arrays.stream(TariffType.values()).map(t -> t.stringTariffType)
                .collect(Collectors.toList());
    }

}
