package com.cdf.factory.common.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.extern.log4j.Log4j2;

/**
 * @Description
 * @author <a href="xuliqun8773@adpanshi.com">xuliqun</a>
 * @date 2019年4月24日 下午9:33:19
 * @version V1.0.1
 */
@Configuration
@EnableCaching
@Log4j2
//@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisCacheAutoConfiguration extends CachingConfigurerSupport {
	
	@Autowired
	RedisSerializer<?>  fastJson2JsonRedisSerializer;
	
	@Autowired	
	StringRedisSerializer stringRedisSerializer;
	
	private Duration timeToLive = Duration.ZERO;

	public void setTimeToLive(Duration timeToLive) {
		this.timeToLive = timeToLive;
	}

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(this.timeToLive)
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJson2JsonRedisSerializer))
				.disableCachingNullValues();

		RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory).cacheDefaults(config)
				.transactionAware().build();

		log.info("自定义RedisCacheManager加载完成");
		return redisCacheManager;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) { // 设置序列化
		// 配置redisTemplate
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(lettuceConnectionFactory);
		
		redisTemplate.setKeySerializer(stringRedisSerializer);// key序列化
		redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer);// value序列化
		redisTemplate.setHashKeySerializer(stringRedisSerializer);// Hash key序列化
		redisTemplate.setHashValueSerializer(fastJson2JsonRedisSerializer);// Hash value序列化
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}
