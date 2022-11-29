package ru.yashta.storageservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yashta.storageservice.model.PathType;
import ru.yashta.storageservice.repository.ItemRepository;
import ru.yashta.storageservice.service.StorageService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StorageServiceApplicationTests {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private StorageService service;

	@Test
	void contextLoads() throws Exception {
		service.load(PathType.CLASSPATH, "src/test/resources/test.xml");
		List<Integer> items = itemRepository.findItems("red", 1);
		assertThat(items).isNotNull().hasSize(2).contains(2, 3);
	}
}
