package ru.bootcamp.brt.model.enums;

public enum TariffType {
    UNLIMITED("06"),
    MINUTE("03"),
    ORDINARY("11");

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
                tariffType = ORDINARY;
                break;
            default:
                throw new IllegalStateException("Такого тарифа нет в базе: " + inputTariffType);
        }
        return tariffType;
        
    }

}
