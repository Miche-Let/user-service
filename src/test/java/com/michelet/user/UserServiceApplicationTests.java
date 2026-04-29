package com.michelet.user;

import com.michelet.user.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@Import(RedisConfig.class)
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
