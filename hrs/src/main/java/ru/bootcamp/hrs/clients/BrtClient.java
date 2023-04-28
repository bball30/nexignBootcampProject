package ru.bootcamp.hrs.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Клиент для взаимодействия с brt сервисом
 */
@Headers("Cache-Control: no-cache")
@FeignClient(name = "brt")
public interface BrtClient {
    @GetMapping("/cdr/randomCdrPlus")
    Resource randomCdrPlus();

    @GetMapping("/cdr/existedCdrPlus")
    Resource existedCdrPlus();
}
