package com.cdf.factory.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.utils.DateUtils;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Series;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cdf.factory.dao.mongodb.NamespaceDao;
import com.cdf.factory.entity.Namespace;
import com.cdf.factory.service.rabbitmq.Sender;
import com.cdf.factory.service.stream.RabbitmqSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FactoryServiceApplicationTests {
	@Autowired
	NamespaceDao namespaceDao;
	
	@Autowired
	private RabbitmqSender rabbitmqSender;
	
	@Autowired
	private InfluxDB influxDB;

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
	
	@Test
	public void sendMessageTest1() throws InterruptedException {
       for(int i = 0; i < 1; i ++){
    	   try {
		       Map<String, Object> properties = new HashMap<String, Object>();
		       properties.put("SERIAL_NUMBER", "12345");
		       properties.put("BANK_NUMBER", "abc");
		       properties.put("PLAT_SEND_TIME", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
	    	   rabbitmqSender.sendMessage("Hello, I am amqp sender num :" + i, properties);
              
           } catch (Exception e) {
        	   System.out.println("--------error-------");
               e.printStackTrace(); 
           }
       }
       TimeUnit.SECONDS.sleep(2000);
	}
	
	@Test
	public void testInfluxDb() {
		Query query = new Query("SELECT * FROM temperature", "factory");
		QueryResult queryResult = influxDB.query(query);
		queryResult.getResults().stream().forEach(ll -> {
			List<Series> series = ll.getSeries();
			for (Series serie : series) {
				List<List<Object>> values = serie.getValues();// 字段字集合
				List<String> colums = serie.getColumns();// 字段名
				System.out.println("colums:" + colums);
				for (List<Object> n : values) {
					System.out.println("value:" + n);
				}
			}
		});
	}
	
	/*
	 * @Test public void testsss() { Message message = new Message();
	 * message.setAppToken("AT_qHT0cTQfLwYOlBV9cJj9zDSyEmspsmyM");
	 * message.setContentType(Message.CONTENT_TYPE_TEXT);
	 * message.setContent("不加限制的自由是很可怕的，因为很容易让任何人滑向深渊。");
	 * message.setUid("c1BcpqxEbD8irqlGUh9BhOqR2BvH8yWZ");
	 * message.setUrl("http://wxpuser.zjiecode.com"); Result<List<MessageResult>>
	 * result = WxPusher.send(message); }
	 */
}
