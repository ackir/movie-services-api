package com.ack.movie.services.api.configuration;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfiguration {

    public static final String MOVIE_CACHE = "movie_cache";

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();

        Cache movieCache = new ConcurrentMapCache(MOVIE_CACHE,
                CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES)
                        .build()
                        .asMap(), false);

        simpleCacheManager.setCaches(Collections.singleton(movieCache));

        return simpleCacheManager;
    }
}
