package ru.yashta.storageservice.config;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.yashta.storageservice.model.Storage;

@Configuration
public class AppConfig {

    @SneakyThrows
    @Bean
    public Unmarshaller storageUnmarshaller() {
        JAXBContext context = JAXBContext.newInstance(Storage.class);
        return context.createUnmarshaller();
    }
}
