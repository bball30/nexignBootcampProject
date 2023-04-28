package ru.bootcamp.brt.generator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.bootcamp.brt.exeptions.InvalidDataException;
import ru.bootcamp.brt.model.Abonent;
import ru.bootcamp.brt.model.Tariff;
import ru.bootcamp.brt.repositories.AbonentRepository;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Component
@Slf4j
public class Generator {

    private final AbonentRepository abonentRepository;

    private int monthCount = 0;

    @Value("${generator.options.directoryPath}")
    private String directoryPath;

    public Generator(AbonentRepository abonentRepository) {
        this.abonentRepository = abonentRepository;
    }

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
        final Tariff tariffX = new Tariff(
                "82",
                "Тариф Х",
                0L,
                0L,
                0f,
                1.5f,
                true,
                true,
                false
        );
        final List<Tariff> tariffList = List.of(tariffUnlimited, tariffMinute, tariffClassic, tariffX);
        final List<String> phoneNumberList = generateTelNumbers(n);
        List<Abonent> abonentList = new ArrayList<>();

        for (String phoneNumber : phoneNumberList) {
            long lowerBound = -2000L;
            int range = 10000;
            float randomBalance = (float) random.nextInt(range) + lowerBound;
            boolean romashkaOperator = random.nextBoolean();
            Abonent abonent = new Abonent(
                    phoneNumber,
                    tariffList.get(random.nextInt(4)),
                    randomBalance,
                    romashkaOperator
            );
            abonentList.add(abonent);
        }
        return abonentList;
    }

    public File generateCdr() {
        final List<String> typesOfCallList = List.of("01", "02");
        List<String> phonesList = abonentRepository.findAll().stream()
                .map(Abonent::getTelNumber).collect(Collectors.toList());

        StringBuilder cdr = new StringBuilder();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        Random random = new Random();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(
                        String.format("%s/random_cdr.txt", directoryPath)
                ))) {
            for (String phoneNumber : phonesList) {
                String callingPhoneNumber = phonesList.get(random.nextInt(phonesList.size()));
                while (callingPhoneNumber.equals(phoneNumber)) {
                    callingPhoneNumber = phonesList.get(random.nextInt(phonesList.size()));
                }
                final LocalDateTime monthToStart = LocalDateTime.of(2023, 1, 1, 0, 0, 0)
                        .plusMonths(monthCount);
                LocalDateTime startTime = monthToStart.plusDays(random.nextInt(30))
                        .plusHours(random.nextInt(24))
                        .plusMinutes(random.nextInt(60))
                        .plusSeconds(random.nextInt(60));
                LocalDateTime endTime = startTime.plusMinutes(random.nextInt(60)).plusSeconds(random.nextInt(60));
                String typeOfCall = typesOfCallList.get(random.nextInt(2));
                cdr.append(typeOfCall).append(",").append(phoneNumber).append(",")
                        .append(callingPhoneNumber).append(",")
                        .append(startTime.format(dateFormat)).append(",")
                        .append(endTime.format(dateFormat)).append("\n");

                int nCalls = 20 + random.nextInt(30);
                for (int i = 0; i < nCalls; i++) {
                    callingPhoneNumber = phonesList.get(random.nextInt(phonesList.size()));
                    while (callingPhoneNumber.equals(phoneNumber)) {
                        callingPhoneNumber = phonesList.get(random.nextInt(phonesList.size()));
                    }
                    startTime = monthToStart.plusDays(random.nextInt(30))
                            .plusHours(random.nextInt(24))
                            .plusMinutes(random.nextInt(60))
                            .plusSeconds(random.nextInt(60));
                    endTime = startTime.plusMinutes(random.nextInt(60)).plusSeconds(random.nextInt(60));
                    typeOfCall = typesOfCallList.get(random.nextInt(2));
                    cdr.append(typeOfCall).append(",").append(phoneNumber).append(",")
                            .append(callingPhoneNumber).append(",")
                            .append(startTime.format(dateFormat)).append(",")
                            .append(endTime.format(dateFormat)).append("\n");
                }
            }
            bufferedWriter.write(cdr.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        monthCount++;

        return new File(String.format("%s/random_cdr.txt", directoryPath));
    }

    public File generateCdrPlus(Resource resource) throws IOException {
        Map<String, Abonent> abonentMap = abonentRepository.findAll().stream()
                .collect(Collectors.toMap(Abonent::getTelNumber, Function.identity()));

        //log.debug(String.valueOf(abonentMap.size()));
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        InputStream inputStream = resource.getInputStream();
        String[] cdr = new BufferedReader(new InputStreamReader(inputStream))
                .lines().parallel().collect(Collectors.joining("\n"))
                .split("\n");

        StringBuilder cdrPlus = new StringBuilder();

        Arrays.stream(cdr).forEach(line -> {
            String numberPhone = line.split(",")[1];
            String callingNumberPhone = line.split(",")[2];
            if (abonentMap.containsKey(numberPhone)) {
                Abonent abonent = abonentMap.get(numberPhone);
                if (abonent.getBalance() > 0) {
                    try {
                        cdrPlus.append(line).append(appendTariffDetails(abonent.getTariff(), numberPhone, callingNumberPhone));
                    } catch (InvalidDataException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //log.info(cdrPlus.toString());

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(
                        String.format("%s/cdr_plus.txt", directoryPath)
        ))) {
            writer.write(cdrPlus.toString());
        }
        return new File(String.format("%s/cdr_plus.txt", directoryPath));
    }

    private String appendTariffDetails(Tariff tariff, String numberPhone, String callingNumberPhone) throws InvalidDataException {
        Abonent abonent = abonentRepository.findByTelNumber(numberPhone);
        Abonent callingAbonent = abonentRepository.findByTelNumber(callingNumberPhone);
        if (abonent == null || callingAbonent == null) throw new InvalidDataException("Ошибка в cdr файле!");

        return "," + tariff.getTariffId() +
                "," + tariff.getFixedPrice() +
                "," + tariff.getFixedIncludedMinutes() +
                "," + tariff.getIncludedPriceForMinute() +
                "," + tariff.getPriceForMinute() +
                "," + tariff.getIncomingPaid() +
                "," + tariff.getOutgoingPaid() +
                "," + tariff.getInsideOperatorPaid() +
                "," + (abonent.isRomashkaOperator() && callingAbonent.isRomashkaOperator()) +
                "\n";
    }
}
