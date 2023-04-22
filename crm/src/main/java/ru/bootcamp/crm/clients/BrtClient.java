package ru.bootcamp.crm.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.bootcamp.crm.dto.AbonentNewDto;

@Headers("Cache-Control: no-cache")
@FeignClient(name = "brt")
public interface BrtClient {

    @PostMapping(value = "brt/abonent", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createAbonent(@RequestBody AbonentNewDto abonentNewDto) throws Exception;
}
