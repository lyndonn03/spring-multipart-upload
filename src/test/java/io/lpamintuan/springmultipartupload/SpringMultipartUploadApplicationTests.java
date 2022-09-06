package io.lpamintuan.springmultipartupload;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class SpringMultipartUploadApplicationTests extends PostGreSQLTestContainer {

	@Test
	void contextLoads() {
	}

}
