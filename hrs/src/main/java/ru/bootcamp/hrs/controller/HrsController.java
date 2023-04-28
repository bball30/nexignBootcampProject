package ru.bootcamp.hrs.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bootcamp.hrs.clients.BrtClient;
import ru.bootcamp.hrs.dto.BillingResponse;
import ru.bootcamp.hrs.services.BillingService;

import java.io.IOException;
import java.util.List;

/**
 * контроллер запросов к hrs сервису
 */
@RestController
@AllArgsConstructor
@RequestMapping("/hrs")
public class HrsController {
    BrtClient brtClient;
    BillingService billingService;

    @GetMapping("/calculateCost")
    public List<BillingResponse> calculateCost() throws IOException {
        Resource resource = brtClient.existedCdrPlus();
        return billingService.calculateCost(resource);
    }
}
