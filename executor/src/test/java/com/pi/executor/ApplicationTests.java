package com.pi.executor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource( properties = {"email.service.username=MOCK_USER", "email.service.password=MOCK_PASSWORD",} )
class ApplicationTests {
	
	@Test
	void contextLoads() {
	}

}
