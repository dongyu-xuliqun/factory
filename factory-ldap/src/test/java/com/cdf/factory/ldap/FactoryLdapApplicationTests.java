package com.cdf.factory.ldap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cdf.factory.ldap.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FactoryLdapApplicationTests {
	
	@Autowired
	PersonRepository personRepository;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void findAll() throws Exception {
		personRepository.findAll().forEach(p -> {
			System.out.println(p);
		});
	}

}
