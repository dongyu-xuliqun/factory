package com.cdf.factory.service.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

  @RabbitListener(queues = "queue_approve")
  public void process(String hello){
    System.out.println("Reciver: "+ hello);
  }
}
