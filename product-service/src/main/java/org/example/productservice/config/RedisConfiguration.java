package org.example.productservice.config;

import io.lettuce.core.resource.ClientResources;
import lombok.Getter;
import lombok.Setter;
import org.example.productservice.dto.ProductDTO;
import org.example.productservice.serializer.ProductSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
@Setter
@Getter
public class RedisConfiguration {
    private String host;
    private int port;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(host);
        redisConfig.setPort(port);

        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder().build();

        return new LettuceConnectionFactory(redisConfig, clientConfig);
    }

    @Bean
    public RedisTemplate<String, ProductDTO> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, ProductDTO> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new ProductSerializer());
        return redisTemplate;
    }
}
