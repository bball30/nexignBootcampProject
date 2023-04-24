package ru.bootcamp.hrs.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;

@Headers("Cache-Control: no-cache")
@FeignClient(name = "brt")
public interface BrtClient {
    @GetMapping("/cdr/cdrPlus")
    Resource cdrPlus();
}