//package com.tscloud.resource.utils;
//
//import com.tscloud.common.framework.Constants;
//import com.tscloud.common.tool.datagirdtool.*;
//import com.tscloud.common.tool.jobtool.JobTool;
//import com.tscloud.resource.job.CheckResAuthDateJob;
////import com.tscloud.resource.job.LayerCacheJob;
//import com.tscloud.resource.utils.Proxy.Interceptor.RmCacheInterceptor;
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.infinispan.Cache;
//
//public class InitReve {
//
//	private static final Logger log = LogManager.getLogger(InitReve.class);
//
//	/**
//	 * 初始化
//	 */
//	public void init() {
//		try {
//			log.info("初始化成功");
//			//初始化定时任务
//			initSysTask();
//
//			initDataGrid();
////			new InternalMonitor() {
////				@Override
////				public String getInternalSystemName() {
////					return AgentConstants.INTER_SYS_CONSOLE_SYSTEM;
////				}
////			}.initClient();
//		} catch (Exception e) {
//			log.error("系统初始化失败！", e);
//		}
//	}
//
//
//	/**
//	 * 初始化系统定制任务
//	 */
//	public void initSysTask() {
//		try {
//			//检查授权资源是否过期
//			JobTool.getInstance().addJob("CheckResAuthDateJob", "0 0/2 * * * ?", CheckResAuthDateJob.class);
//
//			//清理过期缓存
//			//JobTool.getInstance().addJob("LayerCacheJob", "0 0 1 * * ?", LayerCacheJob.class);
//			log.info("初始化定时任务"+"任务名称："+"[CheckResAuthDateJob]");
//		} catch (Exception e) {
//			log.error("add jobs timed job error", e);
//		}
//	}
//
//	/**
//	 * init datagrid
//	 * 记录代理数据数据网格
//	 * @author shanghuiming
//	 */
//	private void initDataGrid() {
//		DataGridTool.getInstance(RmCacheInterceptor.CACHE_CLUSTER_NAME).getCache(RmCacheInterceptor.CACHE_NAME,true);
//	}
//
//}
