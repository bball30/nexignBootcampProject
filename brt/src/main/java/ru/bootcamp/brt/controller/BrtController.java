package ru.bootcamp.brt.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.bootcamp.brt.model.dto.AbonentNewDto;
import ru.bootcamp.brt.services.AbonentService;

@AllArgsConstructor
@RestController
@RequestMapping("/brt")
public class BrtController {
    AbonentService abonentService;

    @PostMapping(value = "/abonent", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createAbonent(
            @RequestBody AbonentNewDto abonentNewDto
    ) throws Exception {
        abonentService.addNewAbonent(abonentNewDto);
    }
}
