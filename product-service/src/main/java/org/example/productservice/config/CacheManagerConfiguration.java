package org.example.productservice.config;

import org.example.productservice.dto.ProductDTO;
import org.example.productservice.serializer.ProductSerializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


@Configuration
@EnableCaching
public class CacheManagerConfiguration {

    private final CacheConfiguration cacheConfiguration;
    private final ProductSerializer productSerializer;

    public CacheManagerConfiguration(CacheConfiguration cacheConfiguration, ProductSerializer productSerializer) {
        this.cacheConfiguration = cacheConfiguration;
        this.productSerializer = productSerializer;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<String, ProductDTO> redisTemplate) {
        return RedisCacheManager.builder(redisTemplate.getConnectionFactory())
                .cacheDefaults(redisCacheConfiguration())
                .build();
    }
    @Bean
    protected RedisCacheConfiguration redisCacheConfiguration() {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(productSerializer))
                .entryTtl(cacheConfiguration.getTimeToLive());

        if (cacheConfiguration.getKeyPrefix() != null) {
            config = config.prefixCacheNameWith(cacheConfiguration.getKeyPrefix());
        }

        return config;
    }
}
