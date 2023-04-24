package ru.bootcamp.crm.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.bootcamp.crm.dto.*;
import ru.bootcamp.crm.requests.AbonentNewRequest;
import ru.bootcamp.crm.requests.ChangeTariffRequest;
import ru.bootcamp.crm.requests.PayRequest;

import java.io.IOException;

@Headers("Cache-Control: no-cache")
@FeignClient(name = "brt")
public interface BrtClient {

    @PatchMapping(value = "brt/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    AbonentResponseNewDto pay(
            @RequestBody PayRequest payRequest) throws Exception;

    @GetMapping("brt/report/{numberPhone}")
    ReportDto report(
            @PathVariable String numberPhone) throws Exception;

    @PatchMapping(value = "brt/changeTariff", consumes = MediaType.APPLICATION_JSON_VALUE)
    AbonentResponseTariffDto changeTariff(
            @RequestBody ChangeTariffRequest changeTariffRequest) throws Exception;

    @PatchMapping(value = "brt/billing", consumes = MediaType.APPLICATION_JSON_VALUE)
    BillingDto billing() throws IOException;

    @PostMapping(value = "brt/abonent", consumes = MediaType.APPLICATION_JSON_VALUE)
    AbonentNewDto createAbonent(
            @RequestBody AbonentNewRequest abonentNewRequest) throws Exception;
}
