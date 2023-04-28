package ru.bootcamp.brt.exeptions;

/**
 * Исключение при некорректных параметрах в запросе
 */
public class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }
}
