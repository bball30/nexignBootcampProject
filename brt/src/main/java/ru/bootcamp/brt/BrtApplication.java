package ru.bootcamp.brt;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ru.bootcamp.brt.controller.BrtController;

@EnableFeignClients
@SpringBootApplication
@AllArgsConstructor
public class BrtApplication {
    BrtController brtController;

    public static void main(String[] args) {
        SpringApplication.run(BrtApplication.class, args);
    }



}
