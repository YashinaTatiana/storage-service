package ru.yashta.storageservice.bootstrap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import ru.yashta.storageservice.service.StorageService;

@Profile("!test")
@Component
@Slf4j
@RequiredArgsConstructor
public class StorageLoader implements CommandLineRunner {

    @Value("${app.input.link}")
    private String link;

    private final StorageService storageService;

    @Override
    public void run(String... args) {
        if (link != null && !link.isEmpty()) {
            storageService.load(link);
        } else {
            log.warn("{app.input.link} param is not defined");
        }
    }
}
