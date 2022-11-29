package ru.yashta.storageservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ru.yashta.storageservice.model.PathType;
import ru.yashta.storageservice.repository.ItemRepository;
import ru.yashta.storageservice.service.StorageService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class StorageServiceApplicationTests {

	@Test
	void contextLoads() {
	}
}
