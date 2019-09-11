
package com.cdf.factory.common.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqAutoConfiguration {

	@Bean
	public ApplicationRunner runner(AmqpTemplate template) {
		return args -> template.convertAndSend("hello", "foo");
	}

	@Bean
	public Queue myQueue() {
		return new Queue("hello");
	}

/*
 * @RabbitListener(queues = "myqueue") public void listen(String in) {
 * System.out.println(in); } }
 */
}