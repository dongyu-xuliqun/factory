package com.cdf.factory.service.rabbitmq;

import java.util.Date;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cdf.factory.common.constants.RabbitMqConstant;

@Component
public class Sender {
  
  @Autowired
  private AmqpTemplate template;
  
  public void send(){
    String msg = "hello: "+new Date().toString();
    System.out.println("Sender: " + msg);
    template.convertAndSend(RabbitMqConstant.Exchange.EXCHANGE_APPROVE,RabbitMqConstant.Queue.QUEUE_APPROVE, msg);
  }  
}
