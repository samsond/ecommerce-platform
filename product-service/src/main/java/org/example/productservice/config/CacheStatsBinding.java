package org.example.productservice.config;

import com.github.benmanes.caffeine.cache.Cache;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.cache.CaffeineCacheMetrics;
import io.micrometer.core.instrument.config.MeterFilter;
import jakarta.annotation.PostConstruct;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Component;


@Component
public class CacheStatsBinding {

    private final MeterRegistry meterRegistry;
    private final CacheManager cacheManager;
    public CacheStatsBinding(MeterRegistry meterRegistry, CacheManager cacheManager) {
        this.meterRegistry = meterRegistry;
        this.cacheManager = cacheManager;
    }

    @PostConstruct
    public void init() {
        addMeterFilters(); // Add meter filters for renaming tags
        bindCaffeineMetrics(); // Initial binding on startup
    }

    private void bindCaffeineMetrics() {
        // Retrieve the cache named "productCache" from Spring's CacheManager
        org.springframework.cache.Cache springCache = cacheManager.getCache("productCache");

        if (springCache instanceof CaffeineCache caffeineCache) {
            Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();

            Tags cacheTags = Tags.of("cache", "productCache");

            // Static method to monitor and bind metrics
            CaffeineCacheMetrics.monitor(meterRegistry, nativeCache, "productCache", cacheTags);
        }
    }

    private void addMeterFilters() {
        meterRegistry.config().meterFilter(MeterFilter.renameTag("cache.gets", "result", "cache_result"));
        meterRegistry.config().meterFilter(MeterFilter.renameTag("cache.gets", "cache.manager", "cache_manager"));
        meterRegistry.config().meterFilter(MeterFilter.renameTag("cache.gets", "cache", "cache_name"));
    }
}
