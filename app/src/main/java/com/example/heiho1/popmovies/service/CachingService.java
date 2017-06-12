package com.example.heiho1.popmovies.service;

import android.app.IntentService;
import android.util.LruCache;

/**
 * Base generic class from which services which are backed by a single, dedicated cache
 * extending the core LruCache
 */
public abstract class CachingService<T extends LruCache> extends IntentService {
    protected T cache;

    public CachingService(String name) {
        super(name);
    }

    /**
     * Mutator for the cache of this service
     *
     * @param cache  to set as the cache
     */
    public void setCache(T cache) {
        this.cache = cache;
    }

    /**
     * Accessor for the cache of this service
     *
     * @return T  the cache appropriate to this service
     */
    public T getCache() {
        return cache;
    }

    /**
     * Overridden to ensure eviction of all cache entries for garbage collection/file system
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        cache.evictAll();
    }
}
