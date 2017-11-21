package com.jukusoft.erp.gui.cache;

public enum CacheTypes {

    /**
    * cache entries on filesystem
    */
    FILE_CACHE,

    /**
    * cache values on local memory cache
    */
    LOCAL_MEMORY_CACHE,

    /**
    * cache values in distributed hazelcast cache
    */
    HAZELCAST_CACHE

}
