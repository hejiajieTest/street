package com.tscloud.resource.utils;

import com.google.common.collect.Lists;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.infinispan.Cache;
import org.infinispan.configuration.cache.CacheMode;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.configuration.global.GlobalConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.infinispan.manager.EmbeddedCacheManager;
import org.infinispan.transaction.TransactionMode;

import javax.transaction.TransactionManager;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ZK on 9/15/2016.
 */
public class DataGridTool {

    private static final Logger log = LogManager.getLogger(DataGridTool.class);

    /**
     * 线程安全的MAP，比Hashtable的性能高很多倍
     */
    private static Map<String, DataGridTool> tools = new ConcurrentHashMap<>();

    private EmbeddedCacheManager embeddedCacheManager = null;

    private DataGridTool(String clusterName) {
        try {
            String nodeName = "node" + UUID.randomUUID().toString();

            //config ipv4
            System.setProperty("java.net.preferIPv4Stack", "true");
            log.info("Starting DataGrid Server ......");

            embeddedCacheManager = new DefaultCacheManager(
                    GlobalConfigurationBuilder.defaultClusteredBuilder()
                            .transport().clusterName(clusterName)
                            .nodeName(nodeName)
                            .addProperty("configurationFile", "jgroups.xml")
                            .build(),
                    new ConfigurationBuilder()
                            .clustering()
                            .cacheMode(CacheMode.REPL_SYNC).transaction().transactionMode(TransactionMode.NON_TRANSACTIONAL)
                            .autoCommit(false)
                            .build()
            );

            log.info("Starting DataGrid Finish!");
        } catch (Exception e) {
            log.error("DataGrid Start error!", e);
        }
    }

    public synchronized static DataGridTool getInstance(String clusterName) {
        if (tools.containsKey(clusterName)) {
            return tools.get(clusterName);
        } else {
            DataGridTool dataGridTool = new DataGridTool(clusterName);
            tools.put(clusterName, dataGridTool);

            return dataGridTool;
        }
    }

    /**
     * isExistCache
     *
     * @param cacheName
     * @return
     */
    public boolean isExistCache(String cacheName) {
        return embeddedCacheManager.cacheExists(cacheName);
    }

    /**
     * createCache  by name
     *
     * @param cacheName
     * @return
     */
    public Cache createCache(String cacheName) {
        try {
            Configuration configuration = new ConfigurationBuilder()
                    .clustering()
                    .cacheMode(CacheMode.DIST_SYNC)
                    .hash().numOwners(2).transaction().transactionMode(TransactionMode.NON_TRANSACTIONAL)
                    .autoCommit(false)
                    .build();
            embeddedCacheManager.defineConfiguration(cacheName, configuration);
            log.info("Cache Name：【" + cacheName + "】 Create Succeed!");
        } catch (Exception e) {
            log.error("Cache Name：【" + cacheName + "】 Create Failure!", e);
        }

        return embeddedCacheManager.getCache(cacheName);
    }

    /**
     * beginTransaction
     *
     * @param cache
     */
    public void beginTransaction(Cache cache) {
        TransactionManager tm = cache.getAdvancedCache().getTransactionManager();

        try {
            tm.begin();
            log.info("Cache Name:" + cache.getName() + " Transaction  Begin ...");
        } catch (Exception e) {
            log.error("Cache Name:" + cache.getName() + " Transaction  Failure !");
        }
    }

    /**
     * commitTransaction
     *
     * @param cache
     */
    public void commitTransaction(Cache cache) {
        TransactionManager tm = cache.getAdvancedCache().getTransactionManager();

        try {
            tm.commit();
            log.info("Cache Name:" + cache.getName() + " Transaction  Commit!");
        } catch (Exception e) {
            log.error("Cache Name:" + cache.getName() + " Transaction  Failure !");
        }
    }

    /**
     * getCache
     *
     * @param cacheName
     * @return
     */
    public Cache getCache(String cacheName) {
        return embeddedCacheManager.getCache(cacheName);
    }

    /**
     * get cache if it not exist.
     *
     * @param cacheName
     * @param flag      true : if not exist then create, false : call getCache
     * @return cache
     * @author chenlu@ccuu.me
     */
    public Cache getCache(String cacheName, boolean flag) {
        return flag ? (isExistCache(cacheName) ? getCache(cacheName) : createCache(cacheName)) : getCache(cacheName);
    }

    /**
     * getKeys
     *
     * @param cache
     * @param key
     * @return
     */
    public List<String> getKeys(Cache cache, String key) {
        List<String> keyList = Lists.newArrayList();

        while (cache.keySet().iterator().hasNext()) {
            String key_set = (String) cache.keySet().iterator().next();
            if (key_set.indexOf(key) != -1) {
                keyList.add(key_set);
            }
        }

        return keyList;
    }


    /**
     * put val to the cache
     *
     * @param key
     * @param val
     * @param cache
     * @return
     * @author chenlu@ccuu.me
     */
    public boolean put(String key, Object val, Cache cache) {
        boolean isSuc = false;

        try {
//            beginTransaction(cache);
            cache.put(key, val);
//            commitTransaction(cache);
            isSuc = true;
        } catch (Exception e) {
            log.error("put value to cache failure.", e);
        }

        return isSuc;
    }

    /**
     * get value from cache by key
     *
     * @param key
     * @param cache
     * @return
     * @author chenlu@ccuu.me
     */
    public Object get(String key, Cache cache) {
        Object obj = null;

        try {
//            beginTransaction(cache);
            obj = cache.get(key);
//            commitTransaction(cache);
        } catch (Exception e) {
            log.error("get value from cache failure.", e);
        }

        return obj;
    }

    public boolean del(String key, Cache cache) {
        boolean flag = false;

        try {
//            beginTransaction(cache);
            cache.remove(key);
//            commitTransaction(cache);
            flag = true;
        } catch (Exception e) {
            log.error("del data in cache by key failure.", e);
        }

        return flag;
    }
}
