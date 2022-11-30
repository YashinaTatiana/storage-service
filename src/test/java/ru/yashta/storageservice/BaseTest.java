package ru.yashta.storageservice;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test")
public class BaseTest {

    @Container
    private static final PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres")
            .withDatabaseName("storage-test-db")
            .withUsername("test")
            .withPassword("test");
}
