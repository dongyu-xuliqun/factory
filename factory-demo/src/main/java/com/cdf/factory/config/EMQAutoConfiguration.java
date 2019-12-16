package com.cdf.factory.config;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomUtils;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Point.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

@Configuration
//@ConditionalOnClass(InfluxDB.class)
@EnableConfigurationProperties(EMQProperties.class)
public class EMQAutoConfiguration {

	@Autowired
	private InfluxDB influxDB;
	
	private final EMQProperties emqProperties;

	public EMQAutoConfiguration(EMQProperties emqProperties) {
		this.emqProperties = emqProperties;
	}

	@Bean
	public MqttConnectOptions getMqttConnectOptions() {
		MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
		mqttConnectOptions.setUserName(this.emqProperties.getUsername());
		mqttConnectOptions.setPassword(this.emqProperties.getPassword().toCharArray());
		mqttConnectOptions.setServerURIs(new String[] { this.emqProperties.getHostUrl() });
		mqttConnectOptions.setKeepAliveInterval(2);
		return mqttConnectOptions;
	}

	@Bean
	public MqttPahoClientFactory mqttClientFactory() {
		DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
		factory.setConnectionOptions(getMqttConnectOptions());
		return factory;
	}

	// 接收通道
	@Bean
	public MessageChannel mqttInputChannel() {
		return new DirectChannel();
	}
	
	@Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

	@Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        //clientId使用随机数产生
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(String.valueOf(RandomUtils.nextInt()), mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(this.emqProperties.getDefaultTopic());
        messageHandler.setDefaultRetained(false);
        return messageHandler;
    }
	
	// 配置client,监听的topic
	@Bean
	public MessageProducer inbound() {
		MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(String.valueOf(RandomUtils.nextInt()) + "_inbound",
				mqttClientFactory(), "hello", "hello1");
		adapter.setCompletionTimeout(5000);
		adapter.setConverter(new DefaultPahoMessageConverter());
		adapter.setQos(1);
		adapter.setOutputChannel(mqttInputChannel());
		return adapter;
	}

	// 通过通道获取数据
	@Bean
	@ServiceActivator(inputChannel = "mqttInputChannel")
	public  MessageHandler handler() {
		return new MessageHandler() {
			@Override
			public void handleMessage(Message<?> message) throws MessagingException {
				System.out.println(message.toString());				
				String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
				String type = topic.substring(topic.lastIndexOf("/") + 1, topic.length());
				if ("hello".equalsIgnoreCase(topic)) {
					System.out.println("hello,fuckXX," + message.getPayload().toString());					
					Map<String, Object> payload = JSON.parseObject(String.valueOf(message.getPayload()), new TypeReference<Map<String, Object>>(){});
					//insert temperature,device_id=device1,temp=02 hum=66,aaa=10
					Map<String, String> tags = new HashMap<>();
					tags.put("device_id", String.valueOf(payload.get("device_id")));
					tags.put("device_name", String.valueOf(payload.get("device_name")));
					
					Map<String, Object> fields = new HashMap<>();
					fields.put("hum", payload.get("hum"));
					fields.put("temp", payload.get("temp"));
					
					 Builder builder = Point.measurement("temperature");
				        //builder.time(message.getHeaders().getTimestamp(), TimeUnit.MILLISECONDS);
				        builder.tag(tags);
				        builder.fields(fields);
					// 插入influxDB
					
					influxDB.write("factory", "", builder.build());
					
				} else if ("hello1".equalsIgnoreCase(topic)) {
					System.out.println("hello1,fuckXX," + message.getPayload().toString());
				}
			}
		};
	}
}
