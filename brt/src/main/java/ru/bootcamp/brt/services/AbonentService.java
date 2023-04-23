package ru.bootcamp.brt.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bootcamp.brt.exeptions.InvalidDataException;
import ru.bootcamp.brt.model.Abonent;
import ru.bootcamp.brt.model.Tariff;
import ru.bootcamp.brt.model.dto.*;
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
    public AbonentNewDto addNewAbonent(AbonentNewDto abonentNewDto) throws InvalidDataException {
        validateAbonentNewDto(abonentNewDto);
        if (abonentRepository.existsByTelNumber(abonentNewDto.getNumberPhone())) {
            throw new InvalidDataException("Такой абонент уже существует");
        }
        Tariff tariff = tariffRepository.findTariffByTariffId(abonentNewDto.getTariff_id());
        Abonent abonent = new Abonent(
                abonentNewDto.getNumberPhone(),
                tariff,
                Long.parseLong(abonentNewDto.getBalance())
        );
        abonentRepository.save(abonent);

        return new AbonentNewDto(abonent);
    }

    private void validateAbonentNewDto(AbonentNewDto abonentNewDto) throws InvalidDataException {
        StringBuilder message = new StringBuilder();
        boolean isValid = true;
        if (abonentNewDto.getNumberPhone() == null || abonentNewDto.getNumberPhone().equals("")) {
            message.append("Номер телефона не может быть пустым! ");
            isValid = false;
        }
        if (abonentNewDto.getBalance() == null || abonentNewDto.getBalance().equals("")) {
            message.append("Баланс не может быть пустым! ");
            isValid = false;
        }
        if (abonentNewDto.getTariff_id() == null || abonentNewDto.getTariff_id().equals("")) {
            message.append("Тариф не может быть пустым! ");
            isValid = false;
        }
        if (Long.parseLong(abonentNewDto.getBalance()) <= 0) {
            message.append("Баланс должен быть положительным! ");
            isValid = false;
        }
        if (abonentNewDto.getNumberPhone().length() != 11) {
            message.append("Некорректный номер телефона! ");
            isValid = false;
        }

        if (!isValid) throw new InvalidDataException(message.toString());
    }

    @Transactional
    public AbonentResponseNewDto pay(PayDto payDto) throws InvalidDataException {
        validatePayDto(payDto);
        if (!abonentRepository.existsByTelNumber(payDto.getNumberPhone())) {
            throw new InvalidDataException("Такой абонент не существует!");
        }
        Abonent abonent = abonentRepository.findByTelNumber(payDto.getNumberPhone());
        abonent.setBalance(abonent.getBalance() + Long.parseLong(payDto.getMoney()));
        abonentRepository.save(abonent);
        AbonentResponseNewDto abonentResponseNewDto = new AbonentResponseNewDto(
                abonent.getId().toString(),
                abonent.getTelNumber(),
                abonent.getBalance().toString()
        );

        return abonentResponseNewDto;
    }

    private void validatePayDto(PayDto payDto) throws InvalidDataException {
        StringBuilder message = new StringBuilder();
        boolean isValid = true;
        if (payDto.getNumberPhone() == null || payDto.getNumberPhone().equals("")) {
            message.append("Номер телефона не может быть пустым! ");
            isValid = false;
        }
        if (payDto.getMoney() == null || payDto.getMoney().equals("")) {
            message.append("Сумма пополнения не может быть пустой! ");
            isValid = false;
        }
        if (Long.parseLong(payDto.getMoney()) <= 0) {
            message.append("Сумма пополнения должна быть положительной! ");
            isValid = false;
        }
        if (payDto.getNumberPhone().length() != 11) {
            message.append("Некорректный номер телефона! ");
            isValid = false;
        }

        if (!isValid) throw new InvalidDataException(message.toString());
    }

    @Transactional
    public AbonentResponseTariffDto changeTariff(ChangeTariffDto changeTariffDto) throws InvalidDataException {
        validateChangeTariffDto(changeTariffDto);
        if (!abonentRepository.existsByTelNumber(changeTariffDto.getNumberPhone())) {
            throw new InvalidDataException("Такой абонент не существует!");
        }
        if (!tariffRepository.existsByTariffId(changeTariffDto.getTariff_id())) {
            throw new InvalidDataException("Такого тарифа не существует!");
        }
        Abonent abonent = abonentRepository.findByTelNumber(changeTariffDto.getNumberPhone());
        abonent.setTariff(tariffRepository.findTariffByTariffId(changeTariffDto.getTariff_id()));
        abonentRepository.save(abonent);

        AbonentResponseTariffDto abonentResponseTariffDto = new AbonentResponseTariffDto(
                abonent.getId().toString(),
                abonent.getTelNumber(),
                abonent.getTariff().getTariffId()
        );

        return abonentResponseTariffDto;
    }


    private void validateChangeTariffDto(ChangeTariffDto changeTariffDto) throws InvalidDataException {
        StringBuilder message = new StringBuilder();
        boolean isValid = true;
        if (changeTariffDto.getNumberPhone() == null || changeTariffDto.getNumberPhone().equals("")) {
            message.append("Номер телефона не может быть пустым! ");
            isValid = false;
        }
        if (changeTariffDto.getTariff_id() == null || changeTariffDto.getTariff_id().equals("")) {
            message.append("Тариф не может быть пустым! ");
            isValid = false;
        }
        if (changeTariffDto.getNumberPhone().length() != 11) {
            message.append("Некорректный номер телефона! ");
            isValid = false;
        }

        if (!isValid) throw new InvalidDataException(message.toString());
    }
}
