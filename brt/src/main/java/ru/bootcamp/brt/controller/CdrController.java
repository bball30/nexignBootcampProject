package ru.bootcamp.brt.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bootcamp.brt.generator.Generator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Контроллер для работы с cdr файлами
 */
@AllArgsConstructor
@RestController
@RequestMapping("/cdr")
public class CdrController {
    private final Generator generator;

    @GetMapping("/random")
    public Resource randomCdr() throws IOException {
        File file = generator.generateCdr();
        byte[] buffer = new byte[(int) file.length()];

        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(buffer);
        }

        Resource resource = new ByteArrayResource(buffer);
        return resource;
    }

    @GetMapping("/randomCdrPlus")
    public Resource randomCdrPlus() throws IOException {
        File file = generator.generateCdrPlus(randomCdr());
        byte[] buffer = new byte[(int) file.length()];

        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(buffer);
        }

        Resource resource = new ByteArrayResource(buffer);
        return resource;
    }

    @GetMapping("/existedCdrPlus")
    public Resource existedCdrPlus() throws IOException {
        File file = new File("cdr_files/cdr_plus.txt");
        byte[] buffer = new byte[(int) file.length()];

        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(buffer);
        }

        Resource resource = new ByteArrayResource(buffer);
        return resource;
    }


}
