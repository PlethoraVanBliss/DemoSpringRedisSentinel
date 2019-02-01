package com.example.demo;

import com.example.demo.domain.Foo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static org.springframework.util.StringUtils.commaDelimitedListToSet;

//import static io.lettuce.core.ReadFrom.SLAVE_PREFERRED;

@Configuration
@EnableCaching
@EnableRedisRepositories(basePackages = "com.example.demo.repository")
public class AppConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory(
                    RedisSentinelConfiguration sentinelConfiguration) {

        return new LettuceConnectionFactory(sentinelConfiguration,  LettuceClientConfiguration.defaultConfiguration()
        );
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory jedisConnectionFactory) {
        return RedisCacheManager.create(jedisConnectionFactory);
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.redis",ignoreInvalidFields = true)
    RedisSentinelConfiguration sentinelConfiguration (@Value("${spring.redis.sentinel.master}") String master,
                                                     @Value("${spring.redis.sentinel.nodes}") String nodes){


        return new RedisSentinelConfiguration(master, commaDelimitedListToSet(nodes));
   }

    @Bean
    public RedisTemplate<String, Foo> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, Foo> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        return template;
    }

}
