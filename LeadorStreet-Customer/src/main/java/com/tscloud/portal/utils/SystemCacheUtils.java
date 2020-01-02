//package com.tscloud.portal.utils;
//
//import com.tscloud.common.framework.Constants;
//import com.tscloud.common.tool.datagirdtool.DataGridTool;
//import com.tscloud.domain.resource.entity.HttpClientEntity;
//import org.infinispan.Cache;
//
///**
// * Created by shanghuiming on 2016/1/28.
// */
//public class SystemCacheUtils {
//
//    /**
//     * 查询是否需要缓存
//     * @param serviceId
//     * @return
//     */
//    private boolean queryNeedCache(String serviceId){
//        return false;
//    }
//
//    /**
//     * 从datagrid中获取缓存
//     * @return
//     */
//    public static Cache getCache(){
//        //从数据网格中读取数据，如果有，就从缓存中拿，没有，就直接输出
//        DataGridTool tool = DataGridTool.initDataGird();
//        //获取DataGrid缓存
//        return tool.getCache(Constants.DataGrid.PROXY_DATAGRID);
//    }
//
//    /**
//     * 清除缓存
//     * @param key
//     * @param hce
//     */
//    public static void putCache(String key,HttpClientEntity hce) throws Exception{
//        Cache cache = getCache();
//        if(null!=cache){
//            cache.put(key,hce);
//        }
//    }
//
//    /**
//     * 从缓存中获取数据
//     * @param key
//     * @return
//     * @throws Exception
//     */
//    public static HttpClientEntity getData(String key,Cache cache) throws Exception{
//        if(null!=cache.get(key)){
//            return (HttpClientEntity)cache.get(key);
//        }
//        return null;
//    }
//
//    /**
//     * 清除缓存
//     * @param key
//     */
//    public static void clearCache(String key,Cache cache) throws Exception{
//        if(cache.get(key)!=null){
//            cache.remove(key);
//        }
//    }
//}
