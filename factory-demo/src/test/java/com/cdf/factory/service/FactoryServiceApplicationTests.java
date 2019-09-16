package com.cdf.factory.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cdf.factory.dao.mongodb.NamespaceDao;
import com.cdf.factory.entity.Namespace;
import com.cdf.factory.service.rabbitmq.Sender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FactoryServiceApplicationTests {
	@Autowired
	NamespaceDao namespaceDao;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() {
		Namespace nameSpace = new Namespace();
		nameSpace.setId(1);
		nameSpace.setName("徐利群");
		namespaceDao.addNamespace(nameSpace);

		nameSpace.setDescription("lalala");
		namespaceDao.addNamespace(nameSpace);

		nameSpace.setId(1);
		nameSpace.setName("徐利群");
		nameSpace.setCode("001");
		nameSpace.setDescription("hahah");
		namespaceDao.updateNamespace(nameSpace);
	}

	@Autowired
	Sender sender;

	@Test
	public void test1() throws InterruptedException {
		for (int i = 0; i < 100000; i++) {
			//Thread.sleep(1);
			sender.send();
		}
	}
}
