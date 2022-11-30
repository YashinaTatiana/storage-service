package ru.yashta.storageservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.yashta.storageservice.BaseTest;
import ru.yashta.storageservice.model.ItemRequestDto;
import ru.yashta.storageservice.model.LinkType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class ItemServiceTest extends BaseTest {
    @Autowired
    private ItemService itemService;

    @Autowired
    private StorageService storageService;

    @Test
    void testGetItems() {
        storageService.load(LinkType.CLASSPATH, "test.xml");
        List<Integer> items = itemService.getItems(ItemRequestDto.builder().color("red").box(1).build());
        assertThat(items).isNotNull().hasSize(2).contains(2, 3);
    }

    @Test
    void testGetItemsNoResults() {
        storageService.load(LinkType.CLASSPATH, "test.xml");
        List<Integer> items = itemService.getItems(ItemRequestDto.builder().color("noColor").box(1).build());
        assertThat(items).isNotNull().isEmpty();
    }
}
