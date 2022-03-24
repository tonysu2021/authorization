package com.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class AuthServerApplicationTests {

	@Test
	void testBcrypt() {
		String pass = "123456";
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
		String hashPass = bcryptPasswordEncoder.encode(pass);
		System.out.println(hashPass);

		boolean result = bcryptPasswordEncoder.matches("123456", hashPass);

		assertEquals(true, result);
	}

	@Test
	void testBcrypt2() {
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

		boolean result = bcryptPasswordEncoder.matches("123456",
				"$2a$10$WLwpaMP.VUNx8hMpiQmNPeF38UCO7pMh/kRpGg6D0LPxLjw96IGem");
		assertEquals(true, result);
	}

}
