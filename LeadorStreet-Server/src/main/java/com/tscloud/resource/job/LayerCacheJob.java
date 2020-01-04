//package com.tscloud.resource.job;
//
//import com.tscloud.common.framework.spring.ServiceBeanContext;
//import com.tscloud.domain.resource.service.ILayerCacheService;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//
///**
// * 定期清理缓存
// * Created by msj on 2016/8/16.
// */
//public class LayerCacheJob implements Job {
//
//    private ILayerCacheService layerCacheService;
//
//    public LayerCacheJob() {
//
//        layerCacheService = (ILayerCacheService) ServiceBeanContext.getInstance().getBean("layerCacheService");
//    }
//
//    private static final Logger log = LogManager.getLogger(LayerCacheJob.class);
//
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//
//        try {
//            layerCacheService.deleteByDate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
