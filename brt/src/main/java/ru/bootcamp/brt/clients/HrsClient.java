package ru.bootcamp.brt.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.bootcamp.brt.model.BillingResponse;

import java.util.List;

@Headers("Cache-Control: no-cache")
@FeignClient(name = "hrs")
public interface HrsClient {
    @GetMapping("/hrs/calculateCost")
    List<BillingResponse> calculateCost();
}
