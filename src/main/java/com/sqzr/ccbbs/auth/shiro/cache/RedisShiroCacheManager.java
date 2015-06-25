package com.sqzr.ccbbs.auth.shiro.cache;

import com.sqzr.ccbbs.core.cached.ICached;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

public class RedisShiroCacheManager extends AbstractCacheManager {
    private ICached cached;

    @Override
    protected Cache createCache(String cacheName) throws CacheException {
        return new RedisShiroCache<String, Object>(cacheName, cached);
    }

    public ICached getCached() {
        return cached;
    }

    public void setCached(ICached cached) {
        this.cached = cached;
    }
}