package ru.bootcamp.crm.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.bootcamp.crm.clients.BrtClient;
import ru.bootcamp.crm.dto.AbonentNewDto;

@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {
    BrtClient brtClient;

    @PostMapping(value = "/abonent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createClient(
            @RequestBody AbonentNewDto abonentNewDto) throws Exception {
        brtClient.createAbonent(abonentNewDto);
    }


}
