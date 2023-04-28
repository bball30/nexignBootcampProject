package ru.bootcamp.brt.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.bootcamp.brt.model.Abonent;
import ru.bootcamp.brt.repositories.AbonentRepository;
import ru.bootcamp.brt.repositories.TariffRepository;

import java.util.List;

@Component
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private AbonentRepository abonentRepository;
    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private Generator generator;

    @Override
    /**
     * добавляем случайных абонентов в базу клиентов
     */
    public void run(ApplicationArguments args) throws Exception {
        Long abonentsAmount = 50L;
        List<Abonent> abonentList = generator.generateAbonents(abonentsAmount);

        System.out.println(abonentList);
        for (Abonent abonent : abonentList) {
            tariffRepository.save(abonent.getTariff());
            abonentRepository.save(abonent);
        }
    }
}
