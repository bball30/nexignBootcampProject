package ru.bootcamp.brt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bootcamp.brt.model.Abonent;

@Repository
public interface AbonentRepository extends JpaRepository<Abonent, Long> {

    Boolean existsByTelNumber(String telNumber);

    Abonent findByTelNumber(String telNumber);
}
