package ru.bootcamp.crm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.bootcamp.crm.clients.BrtClient;
import ru.bootcamp.crm.dto.AbonentNewDto;
import ru.bootcamp.crm.dto.AbonentResponseTariffDto;
import ru.bootcamp.crm.dto.BillingDto;
import ru.bootcamp.crm.repositories.UserRepository;
import ru.bootcamp.crm.requests.AbonentNewRequest;
import ru.bootcamp.crm.requests.ChangeTariffRequest;

import java.io.IOException;

/**
 * Контроллер команд менеджера
 */
@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {
    BrtClient brtClient;
    UserRepository userRepository;

    @PatchMapping(value = "/changeTariff", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AbonentResponseTariffDto changeTariff(
            @RequestBody ChangeTariffRequest changeTariffRequest) throws Exception {
        return brtClient.changeTariff(changeTariffRequest);
    }

    @PostMapping(value = "/abonent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public AbonentNewDto createClient(
            @RequestBody AbonentNewRequest abonentNewRequest) throws Exception {
        userRepository.addUser(abonentNewRequest.getNumberPhone());
        return brtClient.createAbonent(abonentNewRequest);
    }

    @PatchMapping(value = "/billing", consumes = MediaType.APPLICATION_JSON_VALUE)
    public BillingDto billing() throws IOException {
        return brtClient.billing();
    }
}
