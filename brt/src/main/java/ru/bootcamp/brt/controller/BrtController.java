package ru.bootcamp.brt.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bootcamp.brt.clients.HrsClient;
import ru.bootcamp.brt.generator.Generator;
import ru.bootcamp.brt.model.BillingResponse;
import ru.bootcamp.brt.model.dto.*;
import ru.bootcamp.brt.services.AbonentService;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/brt")
public class BrtController {
    AbonentService abonentService;
    HrsClient hrsClient;
    private final CdrController cdrController;
    private final Generator generator;

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

    @PatchMapping(value = "/billing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BillingResponseDto billing() throws IOException {
        generator.generateCdrPlus(cdrController.randomCdr());
        List<BillingResponse> billingResponse = hrsClient.calculateCost();
        return abonentService.billing(billingResponse);
    }

    @GetMapping("report/{numberPhone}")
    public ReportDto report(
            @PathVariable String numberPhone) throws Exception {
        List<BillingResponse> billingResponse = hrsClient.calculateCost();
        return abonentService.report(numberPhone, billingResponse);
    }

    @GetMapping("/getAbonents")
    public List<String> getAbonents() throws Exception {
        return abonentService.getAbonents();
    }
}
