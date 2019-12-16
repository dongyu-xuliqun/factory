package com.cdf.factory.service.stream;

import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.cdf.factory.streaminterface.Barista;
import com.rabbitmq.client.Channel;

@EnableBinding(Barista.class)
@Service
public class RabbitmqReceiver {

	@StreamListener(Barista.INPUT_CHANNEL)  
    public void receiver(Message message) throws Exception {  
		
    	//手工签收必须要有channel与deliveryTag
    	Channel channel = (Channel) message.getHeaders().get(AmqpHeaders.CHANNEL);
		Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
    	System.out.println("Input Stream 1 接受数据：" + message);
    	System.out.println("消费完毕------------");
    	//批量签收设置为false
    	channel.basicAck(deliveryTag, false);
    }
}
