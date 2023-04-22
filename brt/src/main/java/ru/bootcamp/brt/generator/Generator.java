package ru.bootcamp.brt.generator;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.bootcamp.brt.model.Abonent;
import ru.bootcamp.brt.model.Tariff;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

@Component
public class Generator implements Generatable {

    @Override
    public List<String> generateTelNumbers(Long n) {
        long phoneNumber = 79210043221L;
        List<String> phones = new ArrayList<>();
        LongStream.range(0, n)
                .map(i -> phoneNumber + i * i)
                .mapToObj(String::valueOf)
                .forEach(phones::add);
        return phones;
    }

    public List<Abonent> generateAbonents(Long n) {
        Random random = new Random();
        final Tariff tariffUnlimited = new Tariff(
                "06",
                "Безлимит 300",
                100L,
                300L,
                0f,
                1f,
                true,
                true,
                true
                );
        final Tariff tariffMinute = new Tariff(
                "03",
                "Поминутный",
                0L,
                0L,
                0f,
                1.5f,
                true,
                true,
                true
        );
        final Tariff tariffClassic = new Tariff(
                "11",
                "Обычный",
                0L,
                100L,
                0.5f,
                1.5f,
                false,
                true,
                true
        );
        final List<Tariff> tariffList = List.of(tariffUnlimited, tariffMinute, tariffClassic);
        final List<String> phoneNumberList = generateTelNumbers(n);
        List<Abonent> abonentList = new ArrayList<>();

        for (String phoneNumber : phoneNumberList) {
            long lowerBound = -2000L;
            int range = 10000;
            Long randomBalance = random.nextInt(range) + lowerBound;
            Abonent abonent = new Abonent(
                    phoneNumber,
                    tariffList.get(random.nextInt(3)),
                    randomBalance
            );
            abonentList.add(abonent);
        }
        return abonentList;
    }

}
