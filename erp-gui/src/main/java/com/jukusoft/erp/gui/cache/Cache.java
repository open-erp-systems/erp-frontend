package com.jukusoft.erp.gui.cache;

import com.jukusoft.erp.gui.logging.ILogging;

import java.io.File;

public class Cache {

    protected static CacheManager instance = null;

    public static void init (File cacheDir, ILogging logger) {
        if (instance != null) {
            throw new IllegalStateException("cache was already initialized.");
        }

        instance = CacheManager.createDefaultCacheManager(cacheDir, logger);
    }

    public static CacheManager getInstance () {
        if (instance == null) {
            throw new IllegalStateException("cache isnt initialized yet.");
        }

        return instance;
    }

    public static ICache get (String cacheName) {
        if (!getInstance().containsCache(cacheName)) {
            getInstance().createCache(cacheName, CacheTypes.LOCAL_MEMORY_CACHE);
        }

        return getInstance().getCache(cacheName);
    }

}
