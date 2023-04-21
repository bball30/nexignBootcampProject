package ru.bootcamp.brt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bootcamp.brt.model.Tariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {

    Tariff findTariffByTariffId(String id);



}
