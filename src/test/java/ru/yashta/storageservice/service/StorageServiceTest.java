package ru.yashta.storageservice.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.yashta.storageservice.entity.Box;
import ru.yashta.storageservice.exception.InvalidPathFormatException;
import ru.yashta.storageservice.repository.BoxRepository;
import ru.yashta.storageservice.repository.ItemRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
public class StorageServiceTest {

    @Autowired
    private StorageService storageService;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BoxRepository boxRepository;

    @Test
    void testLoadXmlFile() {
        storageService.load("classpath:test.xml");
        var itemList = itemRepository.findAll();
        var boxList = boxRepository.findAll();
        Assertions.assertThat(itemList).hasSize(6);
        Assertions.assertThat(boxList).hasSize(3);

        Optional<Box> boxOptional = boxList.stream().filter(b -> b.getId().equals(1)).findFirst();
        Assertions.assertThat(boxOptional).isPresent();
        var box = boxOptional.get();
        Assertions.assertThat(box.getItemList())
                .isNotNull()
                .hasSize(3);
        Assertions.assertThat(box.getContainedIn())
                .isNull();
    }

    @Test
    void testLoadXmlFile_InvalidPathFormat() {
        assertThrows(InvalidPathFormatException.class, () -> storageService.load("classpath test.xml"));
        Assertions.assertThat(itemRepository.findAll()).isEmpty();
        Assertions.assertThat(boxRepository.findAll()).isEmpty();
    }

    @Test
    void testLoadXmlFile_InvalidPathType() {
        assertThrows(InvalidPathFormatException.class, () -> storageService.load("incorrectType:link"));
        Assertions.assertThat(itemRepository.findAll()).isEmpty();
        Assertions.assertThat(boxRepository.findAll()).isEmpty();
    }
}