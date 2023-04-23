package ru.bootcamp.brt.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bootcamp.brt.model.dto.AbonentNewDto;
import ru.bootcamp.brt.model.dto.ChangeTariffDto;
import ru.bootcamp.brt.model.dto.PayDto;
import ru.bootcamp.brt.services.AbonentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/brt")
public class BrtController {
    AbonentService abonentService;

    @PostMapping(value = "/abonent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAbonent(
            @RequestBody AbonentNewDto abonentNewDto
    ) throws Exception {
        return new ResponseEntity<>(abonentService.addNewAbonent(abonentNewDto), HttpStatus.OK);
    }

    @PatchMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> pay(
            @RequestBody PayDto payDto
    ) throws Exception {
        return new ResponseEntity<>(abonentService.pay(payDto), HttpStatus.OK);
    }

    @PatchMapping(value = "/changeTariff", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeTariff(
            @RequestBody ChangeTariffDto changeTariffDto
    ) throws Exception {
        return new ResponseEntity<>(abonentService.changeTariff(changeTariffDto), HttpStatus.OK);
    }
}
