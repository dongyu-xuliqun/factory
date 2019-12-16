package com.cdf.factory.web;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdf.factory.service.stream.RabbitmqSender;

@RestController
public class StreamController {

	@Autowired
	private RabbitmqSender rabbitmqSender;
	
	@RequestMapping("hello")
	public void sendMessageTest1() throws InterruptedException {
       for(int i = 0; i < 1000000; i ++){
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
       return;
	}
}
