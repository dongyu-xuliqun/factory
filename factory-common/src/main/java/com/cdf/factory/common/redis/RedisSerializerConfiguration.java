package com.cdf.factory.common.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisSerializerConfiguration {
	
	@Bean(name = "stringRedisSerializer")
	public StringRedisSerializer keySerializer() {
		return new StringRedisSerializer();
	}

	@Bean(name = "fastJson2JsonRedisSerializer")
	public RedisSerializer<Object> valueSerializer() {
		return new FastJson2JsonRedisSerializer<>(Object.class);
	}
}
