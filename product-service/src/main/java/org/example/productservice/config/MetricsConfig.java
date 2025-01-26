package org.example.productservice.config;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public Timer productFetchTimer() {
        return Timer.builder("product.fetch.latency")
                .description("Time taken to fetch product details")
                .register(Metrics.globalRegistry);
    }
}
