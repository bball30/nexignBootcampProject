package ru.bootcamp.crm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.bootcamp.crm.clients.BrtClient;
import ru.bootcamp.crm.dto.AbonentResponseNewDto;
import ru.bootcamp.crm.dto.AbonentResponseTariffDto;
import ru.bootcamp.crm.dto.ReportDto;
import ru.bootcamp.crm.requests.PayRequest;

@RestController
@RequestMapping("/abonent")
@AllArgsConstructor
public class AbonentController {
    BrtClient brtClient;

    @PatchMapping(value = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AbonentResponseNewDto pay(
            @RequestBody PayRequest payRequest) throws Exception {
        return brtClient.pay(payRequest);
    }

    @GetMapping("report/{numberPhone}")
    //@PreAuthorize("#numberPhone == authentication.getName()")
    public ReportDto report(
            @PathVariable String numberPhone) throws Exception {
        return brtClient.report(numberPhone);
    }
}
