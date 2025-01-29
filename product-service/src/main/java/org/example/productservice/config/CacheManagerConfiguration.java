package org.example.productservice.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheManagerConfiguration {

    private final CacheConfiguration cacheConfiguration;

    public CacheManagerConfiguration(CacheConfiguration cacheConfiguration) {
        this.cacheConfiguration = cacheConfiguration;
    }

    @Bean
    public CacheManager cacheManager(MeterRegistry meterRegistry) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder().maximumSize(cacheConfiguration.getMaximumSize())
                .expireAfterWrite(cacheConfiguration.getExpireAfterWriteMinutes(), TimeUnit.MINUTES).recordStats());
        return cacheManager;
    }
}
