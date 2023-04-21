package ru.bootcamp.brt.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bootcamp.brt.exeptions.InvalidDataException;
import ru.bootcamp.brt.model.Abonent;
import ru.bootcamp.brt.model.Tariff;
import ru.bootcamp.brt.model.dto.AbonentNewDto;
import ru.bootcamp.brt.repositories.AbonentRepository;
import ru.bootcamp.brt.repositories.TariffRepository;

@Service
public class AbonentService {

    private final AbonentRepository abonentRepository;
    private final TariffRepository tariffRepository;

    public AbonentService(AbonentRepository abonentRepository, TariffRepository tariffRepository) {
        this.abonentRepository = abonentRepository;
        this.tariffRepository = tariffRepository;
    }

    @Transactional
    public void addNewAbonent(AbonentNewDto abonentNewDto) throws InvalidDataException {
        validateAbonentNewDto(abonentNewDto);

        Tariff tariff = tariffRepository.findTariffByTariffId(abonentNewDto.getTariff());
        Abonent abonent = new Abonent(
                abonentNewDto.getTelNumber(),
                tariff,
                Long.parseLong(abonentNewDto.getBalance())
        );
        abonentRepository.save(abonent);

    }

    private void validateAbonentNewDto(AbonentNewDto abonentNewDto) throws InvalidDataException {
        StringBuilder message = new StringBuilder();
        boolean isValid = true;
        if (abonentNewDto.getTelNumber() == null || abonentNewDto.getTelNumber().equals("")) {
            message.append("Номер телефона не может быть пустым!");
            isValid = false;
        }
        if (abonentNewDto.getBalance() == null || abonentNewDto.getBalance().equals("")) {
            message.append("Баланс не может быть пустым!");
            isValid = false;
        }
        if (abonentNewDto.getTariff() == null || abonentNewDto.getTariff().equals("")) {
            message.append("Тариф не может быть пустым!");
            isValid = false;
        }
        if (Long.parseLong(abonentNewDto.getBalance()) <= 0) {
            message.append("Баланс должен быть положительным!");
            isValid = false;
        }

        if (!isValid) throw new InvalidDataException(message.toString());
    }
}
