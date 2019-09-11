package com.cdf.factory.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FactoryServiceApplicationTests {

	@Test
	public void contextLoads() {
		String password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("123456");
		System.out.println(password);
	}

}
