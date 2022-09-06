package io.lpamintuan.springmultipartupload;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostGreSQLTestContainer {

    private static String POSTGRES_VERSION = "14.5-alpine";
    private static PostgreSQLContainer<?> POSTGRES = new PostgreSQLContainer<>("postgres:" + POSTGRES_VERSION);

    @BeforeAll
    public static void setUp() {
        POSTGRES.start();
    }

    @DynamicPropertySource
    public static void dynamicPropertyResource(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);

    }
    
}
